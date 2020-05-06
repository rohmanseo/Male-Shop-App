package com.komsi.maleshop.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.bumptech.glide.Glide
import com.komsi.maleshop.R
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.persistence.Credential
import com.komsi.maleshop.utils.ConstApi
import kotlinx.android.synthetic.main.fragment_add_to_cart_dialog.*

class AddToCartDialogFragment : DialogFragment() {
    companion object {

        fun newInstance(produk: Produk): AddToCartDialogFragment {
            val fragment = AddToCartDialogFragment()
            val args = Bundle()
            args.putParcelable("product", produk)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_to_cart_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle("Add to Cart?")

        val produk = arguments?.getParcelable<Produk>("product")
        val url = ConstApi.PRODUCT_IMAGE_URL.value + produk?.foto
        Glide.with(requireActivity()).load(url).into(imgProduct)
        tvProduct.text = produk?.nama

        btnMinus.setOnClickListener {
            if (tvCount.text.toString().toInt() > 1) {
                tvCount.text = (tvCount.text.toString().toInt() - 1).toString()
            }
        }
        btnPlus.setOnClickListener {
            tvCount.text = (tvCount.text.toString().toInt() + 1).toString()
        }
        btnAdd.setOnClickListener {
            addToCart(produk?.id.toString(),tvCount.text.toString())
        }


    }

    fun addToCart(id:String,qty:String){
        AndroidNetworking.initialize(requireContext())
        AndroidNetworking.post(ConstApi.CART.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(requireContext())}")
                .addBodyParameter("produk_id", id)
                .addBodyParameter("qty",qty)
                .setTag("favorite")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(object : StringRequestListener {
                    override fun onResponse(response: String?) {
                        Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                        dismiss()

                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(requireActivity(), "Error ${anError.toString()}", Toast.LENGTH_SHORT).show()
                        Log.d("cartResponse",anError.toString())
                        dismiss()

                    }
                })
    }
}
