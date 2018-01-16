package demo.sharesdk.cn.test01.mvp.view;

import android.icu.text.StringSearch;

/**
 * Created by QinQinBaoBei on 2018/1/15.
 */

public interface RegisterPresenterListener {
    void success(String s);
    void failed(String s);
    void adminEmpty(String s);
    void pwdEmpty(String s);
    void email(String s);
    void confrim(String s);
}
