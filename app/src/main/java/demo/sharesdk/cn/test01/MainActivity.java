package demo.sharesdk.cn.test01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.sharesdk.cn.test01.mvp.presenter.LoginPresenter;
import demo.sharesdk.cn.test01.mvp.view.LoginPresenterListener;
import demo.sharesdk.cn.test01.mvp.view.activity.SecondActivity;
import demo.sharesdk.cn.test01.util.LoginMD5;

public class MainActivity extends AppCompatActivity implements LoginPresenterListener {
    private long exitTime = 0;
    @BindView(R.id.adminFive)
    EditText mAdminFive;
    @BindView(R.id.pwdFive)
    EditText mPwdFive;
    @BindView(R.id.loginFive)
    Button mLoginFive;
    @BindView(R.id.regFive)
    TextView mRegFive;
    @BindView(R.id.forgetFive)
    TextView mForgetFive;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //调用presenter层的数据
        loginPresenter = new LoginPresenter(this);

    }

    @OnClick({R.id.loginFive, R.id.regFive})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.loginFive:
                //登录
                loginPresenter.getData(mAdminFive.getText().toString().trim(),mPwdFive.getText().toString().trim());
                break;
            case R.id.regFive:
                //跳转注册页面
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                break;
        }
    }

    //接口返回的数据
    @Override
    public void success(String s) {
        if(("登录成功").equals(s)) {
            LoginMD5.getMD5(mPwdFive.getText().toString().trim());
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void failed(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void adminEmpty(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void pwdEmpty(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

    }
//销毁防止内存泄露
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detach();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
