package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komsi.maleshop.R
import com.komsi.maleshop.adapter.CartAdapter
import com.komsi.maleshop.adapter.ProductCallback
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.model.Cart
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.viewmodel.CartViewModel

class CartFragment : Fragment(), ProductCallback {
    private lateinit var adapter: CartAdapter
    private lateinit var rvCart: RecyclerView
    private lateinit var tvTotal: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCart = view.findViewById(R.id.rv_cart) as RecyclerView
        tvTotal = view.findViewById(R.id.tv_total) as TextView
        fetchCart()

    }

    private fun fetchCart() {
        rvCart.itemAnimator = null
        rvCart.layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter(requireContext(), this)
        rvCart.adapter = adapter

        val cartViewModel = ViewModelProvider(
                requireActivity(),
                ViewModelProvider.NewInstanceFactory()
        ).get(CartViewModel::class.java)
        cartViewModel.setList(requireActivity())
        cartViewModel.getList().observe(requireActivity(), androidx.lifecycle.Observer { contents ->
            adapter.setData(contents)
            tvTotal.text = getTotalPrice(contents).toString()
        })
    }

    fun getTotalPrice(list: ArrayList<Cart>): Double{
        var price  = 0.0
        list.map {
            price += it.harga * it.qty.toDouble()
        }
        return price
    }

    override fun onResume() {
        ToolbarHelper.setTitle("Cart")
        super.onResume()
    }

    override fun productOnClick(produk: Produk) {

    }

    override fun onFavoriteClick() {

    }
}