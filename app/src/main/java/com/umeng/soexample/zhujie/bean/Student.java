package com.umeng.soexample.zhujie.bean;

import android.util.Log;

public class Student {
    private String name;
    private String sex;
    private int age;
    private String lick = "鲁华丰";

    public Student(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Student() {

    }

    private Student(String name) {
        this.name = name;
    }


    public void getInfo(String name) {
        this.name = name;
    }

    private void getInfo(String name, String sex,int age) {
        Log.e("name和sex", name + ",sex=" + sex+",age="+age);

    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", lick='" + lick + '\'' +
                '}';
    }
}
