package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.komsi.maleshop.R
import com.komsi.maleshop.adapter.PagerAdapter
import com.komsi.maleshop.helper.ToolbarHelper

class CategoryFragment : Fragment() {

    private lateinit var adapter : PagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var vpLayout: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vpLayout = view.findViewById(R.id.viewPager_id) as ViewPager
        tabLayout = view.findViewById(R.id.tablayout_id) as TabLayout

//        adapter.addFragment(CoatFragment(), "Shirt")
//        adapter.addFragment(CoatFragment(), "Outwear")
//        adapter.addFragment(CoatFragment(), "Underwear")

        //        adapterKategori.addFragment(new CelenapanjangKategori(),("Celana Panjang "));
        //        adapterKategori.addFragment(new CelanapendekKategori(),("Celana Pendek"));
        //        adapterKategori.addFragment(new PoloKategori(),("Polo"));

        setupTabLayout()
    }

    fun setupTabLayout(){
        adapter = PagerAdapter(childFragmentManager)
        adapter.run {
            addFragment(CoatFragment(), getString(R.string.coat))
            addFragment(ShirtFragment(), getString(R.string.shirt))
        }

        vpLayout.adapter = adapter
        vpLayout.offscreenPageLimit = 1
        tabLayout.setupWithViewPager(vpLayout)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }
    override fun onResume() {
        ToolbarHelper.setTitle("Category")
        super.onResume()
    }
}