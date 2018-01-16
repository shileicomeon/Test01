package demo.sharesdk.cn.test01.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import demo.sharesdk.cn.test01.R;
import demo.sharesdk.cn.test01.bean.DataDataBean;
import demo.sharesdk.cn.test01.mvp.view.activity.DetailsActivity;
import demo.sharesdk.cn.test01.mvp.view.activity.SecondActivity;

/**
 * Created by QinQinBaoBei on 2018/1/16.
 */

public class MyProductAdapter extends RecyclerView.Adapter{
    Context context;
    List<DataDataBean.DataBean> dataDataBean;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    public static final int MAX_DISK_CACHE_SIZE = 40 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
    private static final String IMAGE_PIPELINE_CACHE_DIR = "imagepipeline_cache";
    private static ImagePipelineConfig sImagePipelineConfig;
    private static ImagePipelineConfig sOkHttpImagePipelineConfig;

    public MyProductAdapter(Context context, List<DataDataBean.DataBean> dataDataBean) {
        this.context = context;
        this.dataDataBean = dataDataBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.product_recycler_item,null);
        return new ViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder1 holder1 = (ViewHolder1) holder;
        //设置数据
        String[] split = dataDataBean.get(position).getImages().split("\\|");
        holder1.imageView.setImageURI(Uri.parse(split[0]));
        holder1.price.setText(dataDataBean.get(position).getPrice()+"");
        holder1.title.setText(dataDataBean.get(position).getTitle());

        //点击事件
        holder1.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("pid",dataDataBean.get(position).getPscid()+"");
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataDataBean.size();
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {
          SimpleDraweeView imageView;
          TextView title;
          TextView price;
          LinearLayout linearLayout;
        public ViewHolder1(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.simpleDrawee);
            title = itemView.findViewById(R.id.titleTextview);
            price = itemView.findViewById(R.id.price);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }
    /**
     * 配置内存缓存和磁盘缓存
     */
    private static void configureCaches(
            ImagePipelineConfig.Builder configBuilder,
            Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                // Max total size of elements in the cache
                MAX_MEMORY_CACHE_SIZE,
                // Max entries in the cache
                Integer.MAX_VALUE,
                // Max total size of elements in eviction queue
                MAX_MEMORY_CACHE_SIZE,
                // Max length of eviction queue
                Integer.MAX_VALUE,
                // Max cache entry size
                Integer.MAX_VALUE);
        configBuilder
                .setBitmapMemoryCacheParamsSupplier(
                        new Supplier<MemoryCacheParams>() {
                            @Override
                            public MemoryCacheParams get() {
                                return bitmapCacheParams;
                            }
                        })
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(context)
                                .setBaseDirectoryPath(context.getApplicationContext().getCacheDir())
                                .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)
                                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                                .build());
    }
    private static void configureLoggingListeners(ImagePipelineConfig.Builder configBuilder) {
        Set<RequestListener> requestListeners = new HashSet<>();
        requestListeners.add(new RequestLoggingListener());
        configBuilder.setRequestListeners(requestListeners);
    }

    private static void configureOptions(ImagePipelineConfig.Builder configBuilder) {
        configBuilder.setDownsampleEnabled(true);
    }

}
