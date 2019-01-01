package com.umeng.soexample.zhujie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.umeng.soexample.zhujie.bean.MyData;
import com.umeng.soexample.zhujie.bean.Student;
import com.umeng.soexample.zhujie.utils.ZhuJieBind;
import com.umeng.soexample.zhujie.zjbao.AutoObject;
import com.umeng.soexample.zhujie.zjbao.TestRetention;

import java.lang.reflect.Field;

/**
 * 注解？JDK1.5过后的修饰符
 * 作用？简化代码 提高开发效率
 * 注意？肯定是能提高代码开发效率，并不一定能提供程序运行效率
 * 案例2：自定义注解创建对象
 */
public class ZJDemoActivity extends AppCompatActivity {
    @TestRetention("memeda")
    String mStr;
    @AutoObject
    Student mStu;
    @AutoObject
    MyData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjdemo);
        ZhuJieBind.bind(this);
        mStu.getInfo("zjy");
        Toast.makeText(this, mStu.toString(), Toast.LENGTH_SHORT).show();
        data.setData("zjy", "mei");
        Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
    }

    private void getZhujieConent() {
        ZJDemoActivity zjDemoActivity = new ZJDemoActivity();
        Class cls = zjDemoActivity.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            TestRetention annotation = f.getAnnotation(TestRetention.class);
            if (annotation != null) {
                String value = annotation.value();
                Log.e("value", value);
            }
        }
    }
}
