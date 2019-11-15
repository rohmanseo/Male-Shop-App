package com.komsi.maleshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.view.View.*;

public class HomeFragment extends Fragment {

    Button button;
    ViewPager viewPager;
    private List<Slide> lstSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView recyclerView;
    SliderPagerAdapter adapter;
    Handler handler;
    Runnable runnable;
    Timer timer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        sliderpager = view.findViewById(R.id.slider_pager);
        indicator = view.findViewById(R.id.indicator);
        recyclerView=view.findViewById(R.id.rv_Arrival);


        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide1,"Slide pertama"));
        lstSlides.add(new Slide(R.drawable.slide2,"Slide kedua"));
        lstSlides.add(new Slide(R.drawable.slide3,"Slide Ketiga"));
        lstSlides.add(new Slide(R.drawable.slide4,"Slide keempat"));

        adapter = new SliderPagerAdapter(this.getActivity(),lstSlides);
        sliderpager.setAdapter(adapter);

        indicator.setupWithViewPager(sliderpager,true);




         //data

        List<RvNewArrival>lstArrival = new ArrayList<>();
        lstArrival.add(new RvNewArrival("Tolliver-x",R.drawable.bg1,"Rp 400.000"));
        lstArrival.add(new RvNewArrival("AutivFixin",R.drawable.bg2,"Rp 450.000") );
        lstArrival.add(new RvNewArrival("Banana Republic",R.drawable.bg3,"Rp 300.000"));
        lstArrival.add(new RvNewArrival("hemmeh",R.drawable.bg4,"Rp 500.000"));
        lstArrival.add(new RvNewArrival("hmgoeppod",R.drawable.bg5,"Rp 650.000"));
        lstArrival.add(new RvNewArrival("jackNicklaus",R.drawable.bg6,"Rp 325.000"));


        RvArrivalAdapter arrivalAdapter= new RvArrivalAdapter(this.getActivity(),lstArrival);
        recyclerView.setAdapter(arrivalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false));


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                int i =     sliderpager.getCurrentItem();

                if (i==adapter.mList.size()-1){

                    i=0;
                    sliderpager.setCurrentItem(i,true);

                }else {

                    i++;
                    sliderpager.setCurrentItem(i,true);
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);
            }
        },4000,4000);


        return view;


    }


}
