package demo.sharesdk.cn.test01.mvp.module;

import java.util.HashMap;
import java.util.Map;

import demo.sharesdk.cn.test01.bean.LoginBean;
import demo.sharesdk.cn.test01.mvp.view.LoginModuleListener;
import demo.sharesdk.cn.test01.retrofit.BaseObserver;
import demo.sharesdk.cn.test01.retrofit.RetrofitManager;

/**
 * Created by QinQinBaoBei on 2018/1/15.
 */

public class LoginModule {


    public void getData(String admin, String password, final LoginModuleListener loginListener){
        Map<String,String> map = new HashMap<>();
        map.put("mobile",admin);
        map.put("password",password);
        RetrofitManager.get("user/login", map, new BaseObserver<LoginBean>() {
            @Override
            public void success(LoginBean loginBean) {
                    if(loginListener!=null){
                        loginListener.success(loginBean);
                    }
            }

            @Override
            public void failure(int code) {

            }
        });

    }
}
