package com.komsi.maleshop.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.komsi.maleshop.model.Cart
import com.komsi.maleshop.persistence.Credential
import com.komsi.maleshop.utils.ConstApi
import org.json.JSONObject

class CartViewModel : ViewModel(){
    private val listProduk = MutableLiveData<ArrayList<Cart>>()

    fun setList(context: Context){
        val list = ArrayList<Cart>()
        AndroidNetworking.initialize(context)
        AndroidNetworking.get(ConstApi.CART.value)
                .addHeaders("Authorization", "Bearer ${Credential.getToken(context)}")
                .setTag("coat")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        Log.d("cartResponse",response.toString())
                        val jsonArr = response.getJSONArray("data")

                        for (i in 0 until jsonArr.length()){
                            val jsonObj = jsonArr.getJSONObject(i)
                            val id = jsonObj.getInt("id")
                            val name = jsonObj.getString("nama")
                            val foto = jsonObj.getString("foto")
                            val harga = jsonObj.getString("harga")
                            val detail = jsonObj.getString("diskripsi")

                            val pivot = jsonObj.getJSONObject("pivot")
                            val qty = pivot.getString("qty")

                            val produk = Cart(id, name, foto, harga.toDouble(), detail,qty)
                            list.add(produk)
                        }
                        listProduk.postValue(list)
                    }

                    override fun onError(anError: ANError) {
                        Log.d("coatResponse",anError.toString())
                    }
                })
    }

    fun getList(): LiveData<ArrayList<Cart>> {
        return listProduk
    }
}