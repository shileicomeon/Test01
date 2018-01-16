package demo.sharesdk.cn.test01.mvp.module;

import java.util.HashMap;
import java.util.Map;

import demo.sharesdk.cn.test01.bean.LoginBean;
import demo.sharesdk.cn.test01.bean.RegisterBean;
import demo.sharesdk.cn.test01.mvp.view.LoginModuleListener;
import demo.sharesdk.cn.test01.mvp.view.RegModuleListener;
import demo.sharesdk.cn.test01.retrofit.BaseObserver;
import demo.sharesdk.cn.test01.retrofit.RetrofitManager;

/**
 * Created by QinQinBaoBei on 2018/1/15.
 */

public class RegisterModule {


    public void getData(String admin, String password, final RegModuleListener regModuleListener){
        Map<String,String> map = new HashMap<>();
        map.put("mobile",admin);
        map.put("password",password);
        RetrofitManager.get("user/reg", map, new BaseObserver<RegisterBean>() {
            @Override
            public void success(RegisterBean registerBean) {
                    if(regModuleListener!=null){
                        regModuleListener.success(registerBean);
                    }
            }

            @Override
            public void failure(int code) {

            }
        });

    }
}
