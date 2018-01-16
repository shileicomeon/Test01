package demo.sharesdk.cn.test01;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class IApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
