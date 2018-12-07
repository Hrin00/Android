package com.example.mashiro.experiment4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Setting extends AppCompatActivity {
    private boolean isshake;
    private boolean isnight;
    private boolean isalarm;
    private Switch switch_shake;
    private Switch switch_night;
    private Switch switch_alarm;
    private Toolbar toolbar;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        preferences = getSharedPreferences("setting",MODE_PRIVATE);

        switch_shake = (Switch) findViewById(R.id.switch_shake);
        switch_night = (Switch) findViewById(R.id.switch_night);
        switch_alarm = (Switch) findViewById(R.id.switch_remind);

        isshake = preferences.getBoolean("shake",false);
        isnight = preferences.getBoolean("night",false);
        isalarm = preferences.getBoolean("alarm",false);

        if(isshake){
            switch_shake.setChecked(true);
        }
        if(isnight){
            switch_night.setChecked(true);
        }
        if(isalarm){
            switch_alarm.setChecked(true);
        }

        switch_shake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isshake = b;
            }
        });
        switch_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isnight = b;
            }
        });
        switch_alarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isalarm = b;
            }
        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        editor = preferences.edit();
        editor.putBoolean("shake",isshake);
        editor.putBoolean("night",isnight);
        editor.putBoolean("alarm",isalarm);
        editor.apply();
    }


}
