package com.komsi.maleshop.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.androidnetworking.interfaces.StringRequestListener
import com.komsi.maleshop.R
import com.komsi.maleshop.adapter.CartAdapter
import com.komsi.maleshop.adapter.ProductCallback
import com.komsi.maleshop.helper.ToolbarHelper
import com.komsi.maleshop.model.Cart
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.persistence.Credential
import com.komsi.maleshop.ui.activity.AlamatActivity
import com.komsi.maleshop.ui.activity.PaymentActivity
import com.komsi.maleshop.utils.ConstApi
import com.komsi.maleshop.utils.toaster
import com.komsi.maleshop.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.*
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.NumberFormat

class CartFragment : Fragment(), ProductCallback, View.OnClickListener {
    private lateinit var adapter: CartAdapter
    private lateinit var rvCart: RecyclerView
    private lateinit var tvTotal: TextView
    private lateinit var dialogLoading: DialogFragmentLoading
    private lateinit var tvAddressShip: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCart = view.findViewById(R.id.rv_cart) as RecyclerView
        tvTotal = view.findViewById(R.id.tv_total) as TextView
        tvAddressShip = view.findViewById(R.id.tvAddressShip) as TextView
        dialogLoading = DialogFragmentLoading()

        fetchProfileData()

        btn_open_alamat.setOnClickListener {
            val intent = Intent(activity, AlamatActivity::class.java)
            startActivity(intent)
        }

        btnOrder.setOnClickListener(this)
        fetchCart()

        btnOrder.setOnClickListener {
            val intent = Intent(activity, PaymentActivity::class.java)
            startActivity(intent)
        }

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
            tvTotal.text = getTotalPrice(contents)
        })
    }

    private fun fetchProfileData() {
        AndroidNetworking.initialize(requireContext())
        AndroidNetworking.post(ConstApi.PROFILE.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(requireContext())}")
                .setTag("profile")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("profileResponse", response.toString())
                        tvAddressShip.text = response.getString("alamat")

                    }

                    override fun onError(anError: ANError?) {
                        Log.d("profileResponse", anError.toString())
                    }
                })
    }

    fun getTotalPrice(list: ArrayList<Cart>): String {
        var price = 0.0
        list.map {
            price += it.harga * it.qty.toDouble()
        }
        return addCurrency(price)
    }
    fun addCurrency(price: Double): String{
        val formatter: NumberFormat = DecimalFormat("#,###")
        return "Rp ${formatter.format(price)}"
    }
    override fun onResume() {
        ToolbarHelper.setTitle(getString(R.string.cart))
        fetchProfileData()
        super.onResume()
    }

    override fun productOnClick(produk: Produk) {

    }

    override fun onFavoriteClick() {

    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.btnOrder -> {
                dialogLoading.show(requireFragmentManager(), "order")
                processOrder()
                fetchCart()
            }
        }
    }

    private fun processOrder() {
        AndroidNetworking.initialize(context)
        AndroidNetworking.post(ConstApi.ORDER.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(requireContext())}")
                .setTag("order")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(object : StringRequestListener {
                    override fun onResponse(response: String?) {
                        "Success".toaster(requireContext())
                        dialogLoading.dismiss()
                    }

                    override fun onError(anError: ANError?) {
                        "Error".toaster(requireContext())
                        dialogLoading.dismiss()
                    }
                })
    }
}