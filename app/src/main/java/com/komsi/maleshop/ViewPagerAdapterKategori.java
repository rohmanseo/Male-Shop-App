package com.komsi.maleshop;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterKategori extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();


    public ViewPagerAdapterKategori(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {



        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentTitleList.size();
    }
    public CharSequence getPageTitle(int position){
        return fragmentTitleList.get(position);
    }

    public void addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);

    }
}
