package demo.sharesdk.cn.test01.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.sharesdk.cn.test01.R;
import demo.sharesdk.cn.test01.adapter.MyProductAdapter;
import demo.sharesdk.cn.test01.bean.DataDataBean;
import demo.sharesdk.cn.test01.mvp.presenter.ProductPresenter;
import demo.sharesdk.cn.test01.mvp.view.ProductPresenterListener;

public class SecondActivity extends AppCompatActivity implements ProductPresenterListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ProductPresenter productPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        //调用数据
        productPresenter = new ProductPresenter(this);
        productPresenter.getData();

    }


    @Override
    public void success(List<DataDataBean.DataBean> dataDataBean) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this));
        MyProductAdapter myProductAdapter = new MyProductAdapter(SecondActivity.this,dataDataBean);
        mRecyclerView.setAdapter(myProductAdapter);
    }

    //防止内存泄露
    @Override
    protected void onDestroy() {
        super.onDestroy();
        productPresenter.detach();
    }
}
