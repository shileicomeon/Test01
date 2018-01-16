package demo.sharesdk.cn.test01.mvp.presenter;

import demo.sharesdk.cn.test01.bean.AddCartBean;
import demo.sharesdk.cn.test01.mvp.module.AddCartModule;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class AddCartPresenter {
    AddCartPresenterListener addCartPresenterListener;
    private final AddCartModule addCartModule;

    public AddCartPresenter(AddCartPresenterListener addCartPresenterListener) {
        this.addCartPresenterListener = addCartPresenterListener;
        addCartModule = new AddCartModule();
    }

    public void getData(String pid){
        addCartModule.getData(pid, new AddCartModule.CartModuleListener() {
            @Override
            public void success(AddCartBean addCartBean) {
                if(addCartPresenterListener!=null){

                        addCartPresenterListener.success(addCartBean.getMsg());

                }
            }
        });

    }

    public void detach(){
        addCartPresenterListener=null;
    }

    public interface AddCartPresenterListener{
        void success(String s);
    }
}
