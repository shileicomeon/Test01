package demo.sharesdk.cn.test01.mvp.view;

import java.util.List;

import demo.sharesdk.cn.test01.bean.CartBean;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public interface CartPresenterListener {
     void success(List<CartBean.DataBean> group,List<List<CartBean.DataBean.ListBean>> child);
}
