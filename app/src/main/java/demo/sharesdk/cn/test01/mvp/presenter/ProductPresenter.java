package demo.sharesdk.cn.test01.mvp.presenter;

import java.util.List;

import demo.sharesdk.cn.test01.bean.DataDataBean;
import demo.sharesdk.cn.test01.mvp.module.ProducetsModule;
import demo.sharesdk.cn.test01.mvp.view.ProductModuleListener;
import demo.sharesdk.cn.test01.mvp.view.ProductPresenterListener;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class ProductPresenter {
    ProductPresenterListener productPresenterListener;
    private final ProducetsModule producetsModule;

    public ProductPresenter(ProductPresenterListener productPresenterListener) {
        this.productPresenterListener = productPresenterListener;
        producetsModule = new ProducetsModule();
    }
   //获取数据回传到vire层
    public void getData(){
           producetsModule.getData(new ProductModuleListener() {
               @Override
               public void success(DataDataBean dataDataBean) {
                   if(productPresenterListener!=null){
                       productPresenterListener.success(dataDataBean.getData());
                   }
               }
           });
    }
     public void detach(){
        productPresenterListener=null;
     }
}
