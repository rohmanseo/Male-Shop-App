package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komsi.maleshop.R
import com.komsi.maleshop.adapter.ProductAdapter
import com.komsi.maleshop.adapter.ProductCallback
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.helper.FragmentHelper
import com.komsi.maleshop.utils.visible
import com.komsi.maleshop.viewmodel.CoatViewModel

class CoatFragment : Fragment(), ProductCallback {
    private lateinit var adapter: ProductAdapter
    private lateinit var rvCategory: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        lstArrival.add(new Produk("1","Tolliver-x",R.drawable.bg1,"Rp 400.000","-",0.0,"-","-"));
//        lstArrival.add(new Produk("2","AutivFixin",R.drawable.bg2,"Rp 450.000","-",0.0,"-","-") );
//        lstArrival.add(new Produk("1","Banana Republic",R.drawable.bg3,"Rp 300.000","-",0.0,"-","-"));
//        lstArrival.add(new Produk("1","hemmeh",R.drawable.bg4,"Rp 500.000","-",0.0,"-","-"));
//        lstArrival.add(new Produk("1","hmgoeppod",R.drawable.bg5,"Rp 650.000","-",0.0,"-","-"));
//        lstArrival.add(new Produk("2","jackNicklaus",R.drawable.bg6,"Rp 325.000","-",0.0,"-","-"));
        rvCategory = view.findViewById(R.id.rvCategory) as RecyclerView
        fetchCoat()

    }

    private fun fetchCoat() {

        rvCategory.itemAnimator = null
        rvCategory.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = ProductAdapter(requireContext(),this)
        adapter.hasStableIds()
        rvCategory.adapter = adapter

        val coatViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(CoatViewModel::class.java)
        coatViewModel.setList(requireActivity())
        coatViewModel.getList().observe(requireActivity(), androidx.lifecycle.Observer { contents ->
            Log.d("contentSize",contents.toString())
            adapter.setData(contents)

        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun productOnClick(produk: Produk) {
        FragmentHelper.replaceFragmentToDetail(requireActivity().supportFragmentManager,produk)
    }

    override fun onFavoriteClick() {
        fetchCoat()
    }
}