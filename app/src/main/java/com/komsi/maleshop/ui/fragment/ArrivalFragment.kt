package com.komsi.maleshop.ui.fragment

import android.os.Bundle
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
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.helper.FragmentHelper
import com.komsi.maleshop.viewmodel.ArrivalViewModel

class ArrivalFragment : Fragment(), ProductCallback {
    private lateinit var adapter: ProductAdapter
    private lateinit var rvProduct: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_arrival, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvProduct = view.findViewById(R.id.rvArrival) as RecyclerView
        ToolbarHelper.setTitle("Arrival").displayBackArrow(true)
        fetchSearch()
    }

    private fun fetchSearch() {
        rvProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = ProductAdapter(requireContext(), this)
        rvProduct.adapter = adapter

        val arrivalViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(ArrivalViewModel::class.java)
        arrivalViewModel.setList(requireActivity())
        arrivalViewModel.getList().observe(requireActivity(), androidx.lifecycle.Observer { contents ->
            adapter.setData(contents)
        })
    }

    override fun productOnClick(produk: Produk) {
        FragmentHelper.replaceFragmentToDetail(requireActivity().supportFragmentManager, produk)
    }

    override fun onFavoriteClick() {
        fetchSearch()
    }
}