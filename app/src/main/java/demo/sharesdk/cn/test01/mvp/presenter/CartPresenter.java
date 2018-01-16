package demo.sharesdk.cn.test01.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import demo.sharesdk.cn.test01.bean.CartBean;
import demo.sharesdk.cn.test01.mvp.module.CartModule;
import demo.sharesdk.cn.test01.mvp.view.CartModuleListener;
import demo.sharesdk.cn.test01.mvp.view.CartPresenterListener;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class CartPresenter {

    CartPresenterListener cartPresenterListener;
    private final CartModule cartModule;

    public CartPresenter(CartPresenterListener cartPresenterListener) {
        this.cartPresenterListener = cartPresenterListener;
        cartModule = new CartModule();
    }
 //获取数据
    public void getData(){
        cartModule.getData(new CartModuleListener() {
            @Override
            public void success(CartBean cartBean) {
            //拿到一级二级数据返回去
                List<CartBean.DataBean> group = cartBean.getData();
                List<List<CartBean.DataBean.ListBean>> child = new ArrayList<>();
                for (int i=0;i<group.size();i++){
                    child.add(group.get(i).getList());
                }
                if(cartPresenterListener!=null){
                    cartPresenterListener.success(group,child);
                }
            }
        });
    }
    public void detach(){
        cartPresenterListener=null;
    }
}
