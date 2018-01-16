package demo.sharesdk.cn.test01.mvp.presenter;

import demo.sharesdk.cn.test01.bean.DetailsBean;
import demo.sharesdk.cn.test01.mvp.module.DetailsModule;
import demo.sharesdk.cn.test01.mvp.view.DetailsModuleListener;
import demo.sharesdk.cn.test01.mvp.view.DetailsPresenterListener;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class DetailsPresenter {
    private DetailsPresenterListener presenterListener;
    private final DetailsModule detailsModule;

    public DetailsPresenter(DetailsPresenterListener presenterListener) {
        this.presenterListener = presenterListener;
        detailsModule = new DetailsModule();

    }

    public void getData(String pid){
         detailsModule.getData(pid, new DetailsModuleListener() {
             @Override
             public void success(DetailsBean deatails) {
                 if(presenterListener!=null){
                     presenterListener.success(deatails.getData());
                 }
             }
         });
    }
    public void detach(){
        presenterListener=null;
    }
}
