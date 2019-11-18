package com.komsi.maleshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapterKategori adapterKategori;


    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_category,container,false);

        viewPager = view.findViewById(R.id.viewPager_id);
        adapterKategori = new ViewPagerAdapterKategori(getFragmentManager());

        adapterKategori.addFragment(new KaosKategori(),("Kaos"));
        adapterKategori.addFragment(new KemejaKategori(),("Kemeja"));
        adapterKategori.addFragment(new PoloKategori(),("Polo"));
        adapterKategori.addFragment(new OutwearKategori(),("Outwear"));
        adapterKategori.addFragment(new CelenapanjangKategori(),("Celana Panjang "));
        adapterKategori.addFragment(new CelanapendekKategori(),("Celana Pendek"));
        adapterKategori.addFragment(new UnderwearKategori(),("Underwear"));



        viewPager .setAdapter(adapterKategori);
        tabLayout = view.findViewById(R.id.tablayout_id);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);



            return view;
    }
}
