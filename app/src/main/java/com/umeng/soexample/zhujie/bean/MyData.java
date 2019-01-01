package com.umeng.soexample.zhujie.bean;

public class MyData {
    private String name;
    private String sex;

    public void setData(String name,String sex){
        this.name = name;
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "MyData{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
