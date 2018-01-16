package demo.sharesdk.cn.test01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.sharesdk.cn.test01.mvp.presenter.RegisterPresenter;
import demo.sharesdk.cn.test01.mvp.view.LoginPresenterListener;
import demo.sharesdk.cn.test01.mvp.view.RegisterPresenterListener;

public class RegisterActivity extends AppCompatActivity implements RegisterPresenterListener {

    @BindView(R.id.adminFive)
    EditText mAdminFive;
    @BindView(R.id.pwdFive)
    EditText mPwdFive;
    @BindView(R.id.loginFive)
    Button mLoginFive;
    @BindView(R.id.affirm)
    EditText mAffirm;
    @BindView(R.id.email)
    EditText mEmail;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //告诉presenter层
        registerPresenter = new RegisterPresenter(this);

    }

    //点击注册
    @OnClick({R.id.loginFive, R.id.affirm, R.id.email})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.loginFive:
                String admin = mAdminFive.getText().toString().trim();
                String pwd = mPwdFive.getText().toString().trim();
                String maff = mAffirm.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                //点击注册
                registerPresenter.getData(admin,pwd,maff,email);

                break;
        }
    }

    //返回的数据
    @Override
    public void success(String s) {
        if (("注册成功").equals(s)) {
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failed(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void adminEmpty(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void pwdEmpty(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void email(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void confrim(String s) {
        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.detach();
    }
}
