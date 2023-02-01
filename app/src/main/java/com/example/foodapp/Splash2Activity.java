package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class Splash2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td=new Thread(){

            public void run(){
                try {
                    sleep(800);
                }

                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
                finally{
                    Intent intent =new Intent(Splash2Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };td.start();
    }
}
