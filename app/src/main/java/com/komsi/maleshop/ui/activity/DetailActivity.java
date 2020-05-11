package com.komsi.maleshop.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.komsi.maleshop.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imgProduk;
    WebView web;
    ImageView imgFavourite;

    Boolean favorited = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgProduk = findViewById(R.id.img_produk);
        web = findViewById(R.id.wv_detail);
        imgFavourite = findViewById(R.id.img_favourite);

        imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favorited) {
                    favorited = false;
                } else {
                    favorited = true;
                }
                if (favorited) {
                    Glide.with(DetailActivity.this).load(R.drawable.ic_bookmark_black_50dp).into(imgFavourite);
                    Snackbar.make(view, R.string.favorite, Snackbar.LENGTH_SHORT).show();
                } else {
                    Glide.with(DetailActivity.this).load(R.drawable.ic_bookmark_white_50dp).into(imgFavourite);
                    Snackbar.make(view, R.string.deleteFavorite, Snackbar.LENGTH_SHORT).show();
                }

            }
        });


        Glide.with(this).load(R.drawable.produk).into(imgProduk);

        web.loadData("<body style='margin: 0; padding: 0'><p><strong>PRODUCT DETAILS</strong><br />Shirt by Burton Menswear London</p>\n" +
                "<p>For that thing you RSVPd to<br />Button-down collar<br />Button placket<br />Chest pocket<br />Curved hem<br />Regular fit<br />Just select your usual size<br />PRODUCT CODE<br />1487276</p>\n" +
                "<p><strong>BRAND</strong><br />British brand Burton Menswear London combines a long heritage of tailoring with a modern take on relaxed formal and casualwear to bring an added hint of freshness to every occasion. Expect classic shirting and knitwear.</p>\n" +
                "<p><strong>SIZE &amp; FIT</strong><br />Model's height: 6'2&rdquo;/188 cm<br />Model is wearing: Size Medium</p>", "text/html; charset=utf-8", "UTF-8");

    }
}
