package demo.sharesdk.cn.test01.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import demo.sharesdk.cn.test01.R;

public class CustomLinearLayout extends LinearLayout {
    private int i=1;
    private EditText et;

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.custom_linearlayout,null);
        addView(view);
        Button jia = view.findViewById(R.id.jia);
        Button jian = view.findViewById(R.id.jian);
        et = view.findViewById(R.id.edit);
        et.setText("1");
        jia.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                i = Integer.parseInt(et.getText().toString().trim());
                i++;
                et.setText(i+"");
                if(countListener!=null){
                    countListener.setCount(i);
                }
            }


        });

        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>1){
                    i = Integer.parseInt(et.getText().toString().trim());
                    i--;
                }else{
                    Toast.makeText(context,"数量不能小于1",Toast.LENGTH_SHORT).show();
                }
                et.setText(i+"");
                if(countListener!=null){
                    countListener.setCount(i);
                }
            }
        });
    }




    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    CountListener countListener;
    public void getCount(CountListener countListener){
        this.countListener = countListener;
    }

    public interface CountListener{
        void setCount(int i);
    }





    public void setText(String count){
        et.setText(count);
    }
}