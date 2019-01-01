package com.umeng.soexample.zhujie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umeng.soexample.zhujie.bean.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button Field_Btn;
    private Button Method_Btn;
    private Button Concur_Btn;
    private Student mStu;
    private Class mCls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //首先得到字节码对象
        mStu = new Student();
        mCls = mStu.getClass();
    }

    private void initView() {
        Field_Btn = (Button) findViewById(R.id.Field_Btn);
        Method_Btn = (Button) findViewById(R.id.Method_Btn);
        Concur_Btn = (Button) findViewById(R.id.Concur_Btn);

        Field_Btn.setOnClickListener(this);
        Method_Btn.setOnClickListener(this);
        Concur_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Field_Btn:
                getField();
                setField();
                break;
            case R.id.Method_Btn:
                getMotheds();
                break;
            case R.id.Concur_Btn:
                getConstructors();
                break;
        }
    }

    //反射获取属性

    private void getField() {
        //得到该类的所有成员变量
        Field[] fields = mCls.getDeclaredFields();
        //所有的public修饰的变量
        // aClass.getFields();
        for (Field f : fields) {
            Log.e("变量", f.getName());
        }

        try {
            Field field = mCls.getDeclaredField("lick");
            //java语法规定 私有化的东西只能在本类访问  反射提供一个方法加暴力访问对象
            field.setAccessible(true);
            Object o = field.get(mStu);
            if (o instanceof String) {
                Log.e("通过反射获取属性的值", o.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //给某个属性赋值
    private void setField() {

        try {
            Field field = mCls.getDeclaredField("name");
            field.setAccessible(true);
            //通过反射创建一个该类对象  多态的思想，因为我知道反射的对象是谁了
            Student instance = (Student) mCls.newInstance();
            field.set(instance, "memeda");
            Log.e("给属性赋值", instance.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getMotheds() {
        //得到所有成员方法
        Method[] methods = mCls.getDeclaredMethods();
        for (Method m : methods) {
            Log.e("查看方法", m.getName());
        }
        //参数1：代表找那个方法  参数2:1：传入的是字节码文件，  而且是跟着这个方法里面参数走的
        try {
            Method info = mCls.getDeclaredMethod("getInfo", String.class, String.class, int.class);
            info.setAccessible(true);
            Object instance = mCls.newInstance();
            info.invoke(instance, "zjy", "mei", 18);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //操作构造方法的
    private void getConstructors(){
        //得到所有的构造方法
        Constructor[] con = mCls.getDeclaredConstructors();
        for (Constructor c:con){
            Log.e("构造方法",c.getName());
        }
        //只有一个参数就是通过参数就可以知道那个构造了
        try {
            Constructor constructor = mCls.getDeclaredConstructor(String.class,String.class,int.class);
            Object o = constructor.newInstance("zjy", "mei", 17);
            Log.e("获取构造",o.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
