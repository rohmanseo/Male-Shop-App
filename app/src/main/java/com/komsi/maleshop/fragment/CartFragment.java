package com.komsi.maleshop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.komsi.maleshop.adapter.CartAdapter;
import com.komsi.maleshop.model.Produk;
import com.komsi.maleshop.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    CartAdapter adapter;
    ArrayList<Produk> list;
    TextView hargaProduk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.rv_cart);
        hargaProduk = view.findViewById(R.id.tv_total);

        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        list.add(new Produk("1", "Tolliver-x", R.drawable.bg1, "400000", "-", 0.0, "-","-"));
        list.add(new Produk("2", "AutivFixin", R.drawable.bg2, "450000", "-", 0.0, "-","-"));
        list.add(new Produk("1", "Banana Republic", R.drawable.bg3, "300000", "-", 0.0, "-","-"));

        adapter = new CartAdapter(getActivity(), list,hargaProduk);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}
