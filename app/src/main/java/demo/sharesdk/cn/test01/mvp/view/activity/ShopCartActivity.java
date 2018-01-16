package demo.sharesdk.cn.test01.mvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.sharesdk.cn.test01.MainActivity;
import demo.sharesdk.cn.test01.R;
import demo.sharesdk.cn.test01.adapter.MyAdapter;
import demo.sharesdk.cn.test01.bean.CartBean;
import demo.sharesdk.cn.test01.mvp.presenter.CartPresenter;
import demo.sharesdk.cn.test01.mvp.view.CartPresenterListener;

public class ShopCartActivity extends AppCompatActivity implements CartPresenterListener {

    @BindView(R.id.expandView)
    ExpandableListView expandView;
    @BindView(R.id.checkAll)
    CheckBox mCheckAll;
    @BindView(R.id.zprice)
    TextView mZprice;
    @BindView(R.id.zcount)
    Button mZcount;
    @BindView(R.id.linear)
    RelativeLayout mLinear;
    @BindView(R.id.bianji)
    TextView mBianji;
    private MyAdapter myAdapter;
    private String edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);

        ButterKnife.bind(this);
        CartPresenter myPresenter = new CartPresenter(this);
        myPresenter.getData();


    }

    @OnClick({R.id.checkAll, R.id.bianji,R.id.zcount})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.checkAll:
                myAdapter.checkedAll(mCheckAll.isChecked());
                break;

            case R.id.bianji:

                break;
            case R.id.zcount:


                break;
        }
    }

    @Override
    public void success(List<CartBean.DataBean> group, List<List<CartBean.DataBean.ListBean>> child) {
        myAdapter = new MyAdapter(ShopCartActivity.this, group, child);
        myAdapter.setChangeListener(new MyAdapter.ChangeListener() {
            @Override
            public void setState(boolean flag) {

                mCheckAll.setChecked(flag);
            }

            @Override
            public void setText(String price, String count) {

                mZcount.setText("结算(" + count + ")");
                mZprice.setText("总计:" + price + "¥");

            }
        });
        expandView.setAdapter(myAdapter);

        //默认展开
        for (int i = 0; i < group.size(); i++) {
            expandView.expandGroup(i);
        }
    }
}