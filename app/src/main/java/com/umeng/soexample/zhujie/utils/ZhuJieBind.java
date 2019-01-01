package com.umeng.soexample.zhujie.utils;

import android.app.Activity;
import android.view.View;
import com.umeng.soexample.zhujie.zjbao.AutoObject;
import com.umeng.soexample.zhujie.zjbao.BindView;
import com.umeng.soexample.zhujie.zjbao.ClickView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自动创建对象
 * 1:想要创建对象就要newInstance方法
 */
public class ZhuJieBind {
    //ZJDemoActivity  创建对象
    public static void bind(Object object) {
        Class clas = object.getClass();
        Field[] fields = clas.getDeclaredFields();
        //给ZJDemoActivity里面的一个成员变量创建对象
        for (Field f : fields) {
            //这个方法就是判断这个变量有没有加参数里面的注解
            AutoObject annotation = f.getAnnotation(AutoObject.class);
            if (annotation != null) {
                f.setAccessible(true);
                try {
                    //找到这个变量  因为反射机制提供了一个方法让你通过成员变量就可以获取到一个字节码对象
                    Class cls = f.getType();
                    Constructor constructor = cls.getConstructor();
                    //给ZJDemoActivity里面一个成员变量赋值
                    f.set(object, constructor.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //初始化组件  + 点击事件
    public static void bindView(final Activity activity) {
        Class cla = activity.getClass();
        Field[] field = cla.getDeclaredFields();
        Method[] methods = cla.getDeclaredMethods();
        for (Field f : field) {
            //这里面还是遵循java运行规律的
//            f.setAccessible(true);
            BindView annotation = f.getAnnotation(BindView.class);
            if (annotation != null) {
                View view = activity.findViewById(annotation.value());
                try {
                    f.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (final Method method : methods) {
            method.setAccessible(true);
            ClickView clickView = method.getAnnotation(ClickView.class);
            if (clickView != null && clickView.value() != 0) {
                final View viewById = activity.findViewById(clickView.value());
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            method.invoke(activity, viewById);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

    }
}
