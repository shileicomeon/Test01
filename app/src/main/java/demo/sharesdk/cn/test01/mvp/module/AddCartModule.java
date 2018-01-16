package demo.sharesdk.cn.test01.mvp.module;

import java.util.HashMap;
import java.util.Map;

import demo.sharesdk.cn.test01.bean.AddCartBean;
import demo.sharesdk.cn.test01.retrofit.BaseObserver;
import demo.sharesdk.cn.test01.retrofit.RetrofitManager;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class AddCartModule {

    public void getData(String pid, final CartModuleListener cartModuleListener){
     //   http://120.27.23.105/product/addCart
        Map<String, String> map = new HashMap<>();
        map.put("pid",pid);
        map.put("uid","100");
        map.put("source","android");
        //加入购物车
        RetrofitManager.get("product/addCart", map, new BaseObserver<AddCartBean>() {

            @Override
            public void success(AddCartBean addCartBean) {
                if(cartModuleListener!=null){
                    cartModuleListener.success(addCartBean);
                }
            }
            @Override
            public void failure(int code) {

            }
        });
    }
    public interface CartModuleListener{
        void success(AddCartBean addCartBean);
    }

}
