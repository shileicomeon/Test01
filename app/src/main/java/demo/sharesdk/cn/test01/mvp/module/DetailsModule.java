package demo.sharesdk.cn.test01.mvp.module;

import java.util.HashMap;
import java.util.Map;

import demo.sharesdk.cn.test01.bean.DetailsBean;
import demo.sharesdk.cn.test01.mvp.view.DetailsModuleListener;
import demo.sharesdk.cn.test01.retrofit.BaseObserver;
import demo.sharesdk.cn.test01.retrofit.RetrofitManager;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class DetailsModule {
    public void getData(String pid, final DetailsModuleListener moduleListener){
        //http://120.27.23.105/product/getProductDetail?pid=1&source=android
        Map<String,String> map = new HashMap<>();
        map.put("pid",pid);
        map.put("source","android");
        RetrofitManager.get("product/getProductDetail", map, new BaseObserver<DetailsBean>() {
            @Override
            public void success(DetailsBean detailsBean) {
                      if(moduleListener!=null){
                          moduleListener.success(detailsBean);
                      }
            }

            @Override
            public void failure(int code) {

            }


        });
    }
}
