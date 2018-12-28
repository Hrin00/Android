package com.example.mashiro.experiment4.JavaBean.Bmob;

import cn.bmob.v3.BmobObject;

public class BeanRecord extends BmobObject {
    private int rid;
    private int typeid;
    private String time;
    private double money;


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
