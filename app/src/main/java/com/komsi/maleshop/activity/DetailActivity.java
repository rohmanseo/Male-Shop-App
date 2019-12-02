package com.komsi.maleshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.komsi.maleshop.R;
import com.komsi.maleshop.fragment.DialogReview;

public class DetailActivity extends AppCompatActivity {

    ImageView imgProduk;
    WebView web;
    ImageView imgFavourite;
    RatingBar ratingBar;
    Button btnAddToCart;
    Intent intent;
    TextView tvHargaProduk;
    TextView tvNamaProduk;
    Integer gambar;

    Boolean favorited = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        intent = getIntent();

        imgProduk = findViewById(R.id.img_produk);
        web = findViewById(R.id.wv_detail);
        imgFavourite = findViewById(R.id.img_favourite);
        ratingBar = findViewById(R.id.rating_bar);
        btnAddToCart = findViewById(R.id.btn_add_to_cart);
        tvHargaProduk = findViewById(R.id.tv_harga_produk);
        tvNamaProduk = findViewById(R.id.tv_nama_produk);

        final String nama = intent.getStringExtra("nama");
        String harga = intent.getStringExtra("harga");
        gambar = intent.getIntExtra("gambar",R.drawable.produk);

        tvHargaProduk.setText(harga);
        tvNamaProduk.setText(nama);

        btnAddToCart.setText("Tambahkan Ke Keranjang");


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAddToCart.getText().toString().equals("Tambahkan Ke Keranjang")){
                    Snackbar.make(view, "Ditambahkan ke Keranjang",Snackbar.LENGTH_SHORT).show();
                    btnAddToCart.setText("Lihat Keranjang");
                }else {
                    Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                    intent.putExtra("cart",1);
                    startActivity(intent);
                    finish();
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                Fragment fragment = getSupportFragmentManager().findFragmentByTag("dialog review");
                if (fragment != null){
                    fragmentTransaction.remove(fragment);
                }
                fragmentTransaction.addToBackStack(null);

                DialogFragment dialogReview = new DialogReview();
                dialogReview.show(fragmentTransaction,DialogReview.class.getSimpleName());
            }
        });

        imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favorited) {
                    favorited = false;
                } else {
                    favorited = true;
                }
                if (favorited) {
                    Snackbar.make(view, "Berhasil ditambahkan ke favorite", Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "Dihapus dari favorite", Snackbar.LENGTH_SHORT).show();
                }

            }
        });


        Glide.with(this).load(gambar).into(imgProduk);

        web.loadData("<body style='margin: 0; padding: 0'><p><strong>PRODUCT DETAILS</strong><br />Shirt by Burton Menswear London</p>\n" +
                "<p>For that thing you RSVPd to<br />Button-down collar<br />Button placket<br />Chest pocket<br />Curved hem<br />Regular fit<br />Just select your usual size<br />PRODUCT CODE<br />1487276</p>\n" +
                "<p><strong>BRAND</strong><br />British brand Burton Menswear London combines a long heritage of tailoring with a modern take on relaxed formal and casualwear to bring an added hint of freshness to every occasion. Expect classic shirting and knitwear.</p>\n" +
                "<p><strong>SIZE &amp; FIT</strong><br />Model's height: 6'2&rdquo;/188 cm<br />Model is wearing: Size Medium</p>", "text/html; charset=utf-8", "UTF-8");

    }
}
