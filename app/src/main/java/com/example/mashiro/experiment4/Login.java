package com.example.mashiro.experiment4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button login = (Button)findViewById(R.id.Button_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Main.class);
                EditText id = (EditText) findViewById(R.id.UserId);
                EditText pwd = (EditText) findViewById(R.id.UserPwd);
                intent.putExtra("id",id.getText().toString());
                intent.putExtra("pwd",pwd.getText().toString());
                startActivity(intent);
            }
        });



    }
}
