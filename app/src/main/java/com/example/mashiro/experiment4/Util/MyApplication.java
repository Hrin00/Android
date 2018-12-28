package com.example.mashiro.experiment4.Util;

import android.app.Application;
import android.os.Bundle;

import com.facebook.stetho.Stetho;

public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
