package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.komsi.maleshop.R
import com.komsi.maleshop.adapter.ProductAdapter
import com.komsi.maleshop.adapter.ProductCallback
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.helper.FragmentHelper
import com.komsi.maleshop.viewmodel.WishlistViewModel
import kotlinx.android.synthetic.main.fragment_wishlist.*

class WishlistFragment : Fragment(), ProductCallback {
    private lateinit var adapter: ProductAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchWishlist()
    }

    fun fetchWishlist(){
        recWishlist.itemAnimator = null
        recWishlist.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = ProductAdapter(requireContext(), this)
        recWishlist.adapter = adapter

        val shirtViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(WishlistViewModel::class.java)
        shirtViewModel.setList(requireActivity())
        shirtViewModel.getList().observe(requireActivity(), androidx.lifecycle.Observer { contents ->
            adapter.setData(contents)
        })
    }

    override fun onResume() {
        ToolbarHelper.setTitle("Wishlist")
        super.onResume()
    }

    override fun productOnClick(produk: Produk) {
        FragmentHelper.replaceFragmentToDetail(requireActivity().supportFragmentManager,produk)
    }

    override fun onFavoriteClick() {
        fetchWishlist()
    }
}