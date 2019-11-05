package com.komsi.maleshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        final Intent intent = new Intent(this,LoginActivity.class);
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
