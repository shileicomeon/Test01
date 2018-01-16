package demo.sharesdk.cn.test01.mvp.presenter;

import android.text.TextUtils;

import demo.sharesdk.cn.test01.bean.LoginBean;
import demo.sharesdk.cn.test01.mvp.module.LoginModule;
import demo.sharesdk.cn.test01.mvp.view.LoginModuleListener;
import demo.sharesdk.cn.test01.mvp.view.LoginPresenterListener;

/**
 * Created by QinQinBaoBei on 2018/1/15.
 */

public class LoginPresenter {
    LoginPresenterListener loginPresenterListener;
    private final LoginModule loginModule;

    public LoginPresenter(LoginPresenterListener loginPresenterListener) {
        this.loginPresenterListener = loginPresenterListener;
        loginModule = new LoginModule();

    }

    public void getData(String admin,String pwd){
       //判断用户名不用为空
        if(TextUtils.isEmpty(admin)){
             if(loginPresenterListener!=null){
                 loginPresenterListener.adminEmpty("用户名不能为空");
                 return;
             }
        }
        //判断密码不用为空
        if(TextUtils.isEmpty(pwd)){
            if(loginPresenterListener!=null){
                loginPresenterListener.adminEmpty("密码不能为空");
              return;
            }
        }
        //调用m层的数据
        loginModule.getData(admin, pwd, new LoginModuleListener() {
            @Override
            public void success(LoginBean loginBean) {
                if(loginPresenterListener!=null){

                    loginPresenterListener.success(loginBean.getMsg());

                }
            }
        });
    }
    //防止内存泄露
    public void detach(){
        loginPresenterListener=null;
    }

}
