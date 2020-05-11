package com.komsi.maleshop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.komsi.maleshop.R;

public class RincianActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button button,tambahAlamat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Rincian Pesanan");
        button=findViewById(R.id.beli);
        tambahAlamat=findViewById(R.id.btntambahAlamat);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                button.setBackgroundColor(getResources().getColor(R.color.colorSecond));
//                startActivity(new Intent(RincianActivity.this, Activity_payment.class));
//                finish();
//            }
//        });

        tambahAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(RincianActivity.this,AlamatActivity.class));

            }
        });



    }
}
