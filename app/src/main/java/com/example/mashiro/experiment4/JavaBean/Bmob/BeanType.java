package com.example.mashiro.experiment4.JavaBean.Bmob;

import cn.bmob.v3.BmobObject;

public class BeanType extends BmobObject {
    private int typeid;
    private String name;
    private int img;

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
