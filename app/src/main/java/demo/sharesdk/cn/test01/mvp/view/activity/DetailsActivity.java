package demo.sharesdk.cn.test01.mvp.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.sharesdk.cn.test01.R;
import demo.sharesdk.cn.test01.bean.DetailsBean;
import demo.sharesdk.cn.test01.mvp.presenter.AddCartPresenter;
import demo.sharesdk.cn.test01.mvp.presenter.DetailsPresenter;
import demo.sharesdk.cn.test01.mvp.view.DetailsPresenterListener;

public class DetailsActivity extends AppCompatActivity implements DetailsPresenterListener, AddCartPresenter.AddCartPresenterListener {

    @BindView(R.id.videoView)
    VideoView mVideoView;
    @BindView(R.id.simpleDetails)
    SimpleDraweeView mSimpleDetails;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.price)
    TextView mPrice;
    @BindView(R.id.addCart)
    Button mAddCart;
    private DetailsPresenter detailsPresenter;
    private String pid;
    private AddCartPresenter addCartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
       //加入购物车
        addCartPresenter = new AddCartPresenter(this);

        //得到pid
        pid = getIntent().getStringExtra("pid");
        detailsPresenter = new DetailsPresenter(this);
        detailsPresenter.getData(pid);
        String path = Environment.getExternalStorageDirectory()+"/miniony.mp4";
        //视频播放
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(Uri.parse(path));
        mVideoView.requestFocus();
    }


    @Override
    public void success(DetailsBean.DataBean data) {
        //设置数据
        String[] split = data.getImages().split("\\|");
        mSimpleDetails.setImageURI(Uri.parse(split[0]));
        mTitle.setText(data.getTitle());
        mPrice.setText(data.getPrice()+"");

    }

    //防止内存泄露
    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsPresenter.detach();
    }

    @OnClick(R.id.addCart)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.addCart:
                //加入购物车
                addCartPresenter.getData(pid);

                break;
        }
    }

    @Override
    public void success(String s) {
        if(s.equals("加购成功")){
            Intent intent = new Intent(DetailsActivity.this,ShopCartActivity.class);
            //intent.putExtra("pid",pid);
            startActivity(intent);
        }else{

            Toast.makeText(DetailsActivity.this,s,Toast.LENGTH_SHORT).show();
        }
    }
}
