package com.komsi.maleshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ViewUtils;
import androidx.fragment.app.Fragment;

public class KaosKategori extends Fragment {
    View view;
    private String Merk;
    private String Harga;
    private int Thumbnail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.item_kaos,container,false);
    return view;
    }

    public KaosKategori(){

    }

    public KaosKategori(String merk,String harga,int thumbnail) {
        Merk = merk;
        Harga = harga;
        Thumbnail = thumbnail;
    }

    public String getMerk() {
        return Merk;
    }

    public void setMerk(String merk) {
        Merk = merk;
    }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
