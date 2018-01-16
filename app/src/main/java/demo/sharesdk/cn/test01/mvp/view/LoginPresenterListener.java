package demo.sharesdk.cn.test01.mvp.view;

/**
 * Created by QinQinBaoBei on 2018/1/15.
 */

public interface LoginPresenterListener {
    void success(String s);
    void failed(String s);
    void adminEmpty(String s);
    void pwdEmpty(String s);
}
