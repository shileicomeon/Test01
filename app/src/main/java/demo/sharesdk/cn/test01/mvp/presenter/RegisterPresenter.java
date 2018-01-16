package demo.sharesdk.cn.test01.mvp.presenter;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.sharesdk.cn.test01.bean.RegisterBean;
import demo.sharesdk.cn.test01.mvp.module.RegisterModule;
import demo.sharesdk.cn.test01.mvp.view.LoginPresenterListener;
import demo.sharesdk.cn.test01.mvp.view.RegModuleListener;
import demo.sharesdk.cn.test01.mvp.view.RegisterPresenterListener;

/**
 * Created by QinQinBaoBei on 2018/1/15.
 */

public class RegisterPresenter {
    RegisterPresenterListener registerPresenterListener;
    private final RegisterModule registerModule;


    public RegisterPresenter(RegisterPresenterListener registerPresenterListener) {
        this.registerPresenterListener = registerPresenterListener;
        registerModule = new RegisterModule();
    }

    public void getData(String admin,String pwd,String mpwd,String email){
       //判断用户名不用为空
        if(TextUtils.isEmpty(admin)){
             if(registerPresenterListener!=null){
                 registerPresenterListener.adminEmpty("用户名不能为空");
                 return;
             }
        }
        //判断密码不用为空
        if(TextUtils.isEmpty(pwd)){
            if(registerPresenterListener!=null){
                registerPresenterListener.pwdEmpty("密码不能为空");
               return;
            }
            //判断二次输入密码
            if(TextUtils.isEmpty(mpwd)) {
                if (registerPresenterListener != null) {
                    registerPresenterListener.confrim("密码不能为空");
                    return;
                }
                if(!mpwd.equals(pwd)){
                    if (registerPresenterListener != null) {
                        registerPresenterListener.confrim("两次密码不一样");
                        return;
                    }
                }
            }
            boolean email1 = isEmail(email);
            if(!email1){
                if (registerPresenterListener != null) {
                    registerPresenterListener.email("邮箱格式不正确");
                    return;
                }
            }
        }
        //调用m层的数据
        registerModule.getData(admin, pwd, new RegModuleListener() {
            @Override
            public void success(RegisterBean registerBean) {
                if(registerPresenterListener!=null){

                    registerPresenterListener.success(registerBean.getMsg());

                }
            }
        });
    }
    //防止内存泄露
    public void detach(){
        registerPresenterListener=null;
    }
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
}
