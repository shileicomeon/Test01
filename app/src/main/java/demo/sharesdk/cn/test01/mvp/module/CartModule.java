package demo.sharesdk.cn.test01.mvp.module;

import java.util.HashMap;
import java.util.Map;

import demo.sharesdk.cn.test01.bean.CartBean;
import demo.sharesdk.cn.test01.mvp.view.CartModuleListener;
import demo.sharesdk.cn.test01.retrofit.BaseObserver;
import demo.sharesdk.cn.test01.retrofit.RetrofitManager;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class CartModule {

    public void getData(final CartModuleListener cartModuleListener){
//http://120.27.23.105/product/getCarts

        Map<String, String> map = new HashMap<>();
        map.put("uid","100");
        map.put("source","android");
        RetrofitManager.get("product/getCarts", map, new BaseObserver<CartBean>() {
            @Override
            public void success(CartBean cartBean) {
                    if(cartModuleListener!=null){
                        cartModuleListener.success(cartBean);
                    }
            }

            @Override
            public void failure(int code) {

            }

        });
    }
}
