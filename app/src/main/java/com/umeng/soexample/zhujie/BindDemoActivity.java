package com.umeng.soexample.zhujie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.soexample.zhujie.utils.ZhuJieBind;
import com.umeng.soexample.zhujie.zjbao.BindView;
import com.umeng.soexample.zhujie.zjbao.ClickView;

public class BindDemoActivity extends AppCompatActivity {
    @BindView(R.id.tv_one)
    public TextView tv;
    @BindView(R.id.bt_two)
    public Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_demo);
        ZhuJieBind.bindView(this);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BindDemoActivity.this, "zjy", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @ClickView(R.id.bt_two)
    public void onClick(View view){
        Toast.makeText(this, "zjyyy", Toast.LENGTH_SHORT).show();
    }

}
