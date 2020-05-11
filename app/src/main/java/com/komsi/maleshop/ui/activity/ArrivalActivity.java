package com.komsi.maleshop.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.komsi.maleshop.model.Produk;
import com.komsi.maleshop.R;
import com.komsi.maleshop.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArrivalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);
        recyclerView=findViewById(R.id.rvArrival);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.NewArrival);
        setSupportActionBar(toolbar);


        List<Produk> lstArrival = new ArrayList<>();
//        lstArrival.add(new Produk("1", "Tolliver-x", R.drawable.bg1, "Rp 400.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("2", "AutivFixin", R.drawable.bg2, "Rp 450.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("1", "Banana Republic", R.drawable.bg3, "Rp 300.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("1", "hemmeh", R.drawable.bg4, "Rp 500.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("1", "hmgoeppod", R.drawable.bg5, "Rp 650.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("2", "jackNicklaus", R.drawable.bg6, "Rp 325.000", "-", 0.0, "-","-"));


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, lstArrival);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }
}
