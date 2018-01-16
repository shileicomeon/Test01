package demo.sharesdk.cn.test01.mvp.module;

import java.util.HashMap;
import java.util.Map;

import demo.sharesdk.cn.test01.bean.DataDataBean;
import demo.sharesdk.cn.test01.mvp.view.ProductModuleListener;
import demo.sharesdk.cn.test01.retrofit.BaseObserver;
import demo.sharesdk.cn.test01.retrofit.RetrofitManager;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import retrofit2.Retrofit;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class ProducetsModule {

    //列表页的接口
    public void getData(final ProductModuleListener productModuleListener){
        Map<String,String> map = new HashMap<>();
        map.put("pscid","1");
        map.put("page","1");
        map.put("source","android");
        RetrofitManager.get("product/getProducts", map, new BaseObserver<DataDataBean>() {
            @Override
            public void success(DataDataBean dataDataBean) {
                   if(productModuleListener!=null){
                       //将数据返回
                       productModuleListener.success(dataDataBean);
                   }
            }
            @Override
            public void failure(int code) {

            }

        });
    }
}
