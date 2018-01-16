package demo.sharesdk.cn.test01.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import demo.sharesdk.cn.test01.R;
import demo.sharesdk.cn.test01.bean.CartBean;
import demo.sharesdk.cn.test01.widget.CustomLinearLayout;

public class MyAdapter extends BaseExpandableListAdapter {
    Context context;
    List<CartBean.DataBean> group;
    List<List<CartBean.DataBean.ListBean>> child;
    private Holder1 holder1;
    private Holder2 holder2;
    public MyAdapter(Context context, List<CartBean.DataBean> group, List<List<CartBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_expandable_listview, null);
            holder1 = new Holder1();
            holder1.checkBox = convertView.findViewById(R.id.groupChecked);
            holder1.textview = convertView.findViewById(R.id.groupTitle);
            convertView.setTag(holder1);
        }else{
            holder1 = (Holder1) convertView.getTag();
        }
        //设置显示数据
        holder1.textview.setText(group.get(groupPosition).getSellerName());
        holder1.checkBox.setChecked(group.get(groupPosition).isgChecked());

        //设置一级标题的点击事件
        holder1.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = !group.get(groupPosition).isgChecked();
                Toast.makeText(context,b+"",Toast.LENGTH_SHORT).show();
                group.get(groupPosition).setgChecked(b);
                //拿到你点击孩子的父亲
                List<CartBean.DataBean.ListBean> listBeans = child.get(groupPosition);
                //遍历看看他有几个孩子
                for (int i=0;i<listBeans.size();i++){
                    listBeans.get(i).setcChecked(group.get(groupPosition).isgChecked());
                }
                changeListener.setState(isGroup());

                notifyDataSetChanged();
                sum();
            }
        });


        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, null);
            holder2 = new Holder2();
            holder2.imageView = convertView.findViewById(R.id.simpleDraw);
            holder2.checkBox = convertView.findViewById(R.id.childChecked);
            holder2.title = convertView.findViewById(R.id.childTitle);
            holder2.miaoshu= convertView.findViewById(R.id.miaoShu);
            holder2.price = convertView.findViewById(R.id.childPrice);
            holder2.yprice = convertView.findViewById(R.id.childPriceY);
            holder2.linearLayout = convertView.findViewById(R.id.linearLayoutItem);
            holder2.customLinearLayout = convertView.findViewById(R.id.customLinearLayout);
            convertView.setTag(holder2);
        }else{
            holder2 = (Holder2) convertView.getTag();
        }
        final CartBean.DataBean.ListBean listBean = child.get(groupPosition).get(childPosition);
        //设置显示数据
        holder2.checkBox.setChecked(listBean.iscChecked());
        holder2.title.setText(listBean.getTitle());
        holder2.price.setText("¥"+listBean.getPrice());
        holder2.yprice.setText("¥"+listBean.getBargainPrice());
        holder2.customLinearLayout.setText(listBean.getNum()+"");
        //中划线
        holder2.yprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        // 设置中划线并加清晰
        holder2.yprice.setPaintFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        holder2.miaoshu.setText(listBean.getSubhead());
        String[] split = listBean.getImages().split("\\|");
        holder2.imageView.setImageURI(Uri.parse(split[0]));
        holder2.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CartBean.DataBean.ListBean> listBeans = child.get(groupPosition);
                boolean b = !listBeans.get(childPosition).iscChecked();
                listBeans.get(childPosition).setcChecked(b);
                boolean isno = isChildChange(listBeans);
                //设置一级的状态
                group.get(groupPosition).setgChecked(isno);

                changeListener.setState(isChild());

                notifyDataSetChanged();
                sum();
            }
        });
        //加减号的点击事件
        holder2.customLinearLayout.getCount(new CustomLinearLayout.CountListener() {
            @Override
            public void setCount(int i) {
                listBean.setNum(i);
                sum();
            }
        });
        //删除事件
        holder2.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CartBean.DataBean.ListBean> list = group.get(groupPosition).getList();
                for (int i=0;i<list.size();i++) {
                    if (list.size() > 0) {
                        list.remove(i);
                    }
                }
                if(list.size()==0){
                    child.remove(childPosition);
                    group.remove(groupPosition);
                }

                notifyDataSetChanged();
            }

        });

        return convertView;
    }


    //计算的方法
    public void sum(){
        double price = 0;
        int count = 0;

        for (int i=0;i<group.size();i++){
            List<CartBean.DataBean.ListBean> list = group.get(i).getList();
            for (int j=0;j<list.size();j++){
                if(list.get(j).iscChecked()){
                    price +=list.get(j).getPrice()*list.get(j).getNum();
                    count +=list.get(j).getNum();
                }
            }
        }
        notifyDataSetChanged();
        //接口回调
        if(changeListener!=null) {
            changeListener.setText(price + "", count + "");
        }
    }




    ChangeListener changeListener;
    public void setChangeListener(ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    //接口传值,去改变全选的状态,设置商品的数量价格
    public interface ChangeListener{
        void setState(boolean flag);
        void setText(String price,String count);
    }


    //子类点击改变全选状态
    public boolean isChild(){
        for (int i=0;i<group.size();i++){
            List<CartBean.DataBean.ListBean> list = group.get(i).getList();
            for (int j=0;j<list.size();j++){
                if(!child.get(i).get(j).iscChecked()){
                    return false;
                }
            }
        }
        return true;
    }

    //父类点击改变全选的状态

    public boolean isGroup(){
        for (int i=0;i<group.size();i++){
            if(!group.get(i).isgChecked()){
                return false;
            }
        }
        return true;
    }

    //设置全选
    public void checkedAll(boolean flag){
        if(flag){
            for (int i=0;i<group.size();i++){
                List<CartBean.DataBean.ListBean> list = group.get(i).getList();
                group.get(i).setgChecked(true);
                for (int j=0;j<list.size();j++){
                    child.get(i).get(j).setcChecked(true);
                }
            }
        }else{
            for (int i=0;i<group.size();i++){
                List<CartBean.DataBean.ListBean> list = group.get(i).getList();
                group.get(i).setgChecked(false);
                for (int j=0;j<list.size();j++){
                    child.get(i).get(j).setcChecked(false);
                }
            }
        }
        notifyDataSetChanged();
        sum();
    }


    //如果有意子类没选中就将父类的状态改为true
    public boolean isChildChange(   List<CartBean.DataBean.ListBean> listBeans){
        for (int i=0;i<listBeans.size();i++){
            if(!listBeans.get(i).iscChecked()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class Holder1{
        CheckBox checkBox;
        TextView textview;
    }

    class Holder2{
        LinearLayout linearLayout;
        CustomLinearLayout customLinearLayout;
        CheckBox checkBox;
        SimpleDraweeView imageView;
        TextView title;
        TextView miaoshu;
        TextView price;
        TextView yprice;
    }
}