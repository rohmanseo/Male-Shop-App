package com.komsi.maleshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komsi.maleshop.model.Produk;
import com.komsi.maleshop.R;
import com.komsi.maleshop.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PoloKategori extends Fragment {
    View view;


    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_kaos, container, false);

        recyclerView = view.findViewById(R.id.rv_itemKaos);

        List<Produk> lstArrival = new ArrayList<>();
//        lstArrival.add(new Produk("1", "Tolliver-x", R.drawable.bg1, "Rp 400.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("2", "AutivFixin", R.drawable.bg2, "Rp 450.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("1", "Banana Republic", R.drawable.bg3, "Rp 300.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("1", "hemmeh", R.drawable.bg4, "Rp 500.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("1", "hmgoeppod", R.drawable.bg5, "Rp 650.000", "-", 0.0, "-","-"));
//        lstArrival.add(new Produk("2", "jackNicklaus", R.drawable.bg6, "Rp 325.000", "-", 0.0, "-","-"));


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), lstArrival);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);


        return view;
    }
}
