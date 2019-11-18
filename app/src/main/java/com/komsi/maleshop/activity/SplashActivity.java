package com.komsi.maleshop.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.komsi.maleshop.R;
import com.komsi.maleshop.activity.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        final Intent intent = new Intent(this, LoginActivity.class);
        Thread timer =new Thread(){
            public void run (){
             try{
                 sleep(2000);

             }catch (InterruptedException e){
                 e.printStackTrace();
             }
             finally {
                 startActivity(intent);
                 finish();
             }
            }

        };
        timer.start();
    }
}
