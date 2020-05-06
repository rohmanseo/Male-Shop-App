package com.komsi.maleshop.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.bumptech.glide.Glide
import com.komsi.maleshop.R
import com.komsi.maleshop.model.Cart
import com.komsi.maleshop.persistence.Credential
import com.komsi.maleshop.utils.ConstApi
import java.util.*

class CartAdapter(private val context: Context,private val callback:ProductCallback) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    val list = ArrayList<Cart>()

    fun setData(item: ArrayList<Cart>) {
        this.list.clear()
        this.list.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(ConstApi.PRODUCT_IMAGE_URL.value + list[position].foto).into(holder.imgCart)
        holder.cartTitle.text = list[position].nama
        holder.cartPrize.text = list[position].harga.toString()
        holder.tvQty.text = list[position].qty

        holder.imgRemove.setOnClickListener {
            deteteCartItem(list[position].id.toString(),position)
        }
    }

    private fun deteteCartItem(id: String, position: Int) {
        AndroidNetworking.initialize(context)
        AndroidNetworking.delete(ConstApi.CART.value+ "/$id")
                .addHeaders("Authorization", "Bearer ${Credential.getToken(context)}")
                .addBodyParameter("produk_id", id)
                .setTag("favorite")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(object : StringRequestListener {
                    override fun onResponse(response: String?) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        list.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemChanged(position)
                    }

                    override fun onError(anError: ANError?) {
                        Toast.makeText(context, "Error ${anError.toString()}", Toast.LENGTH_SHORT).show()
                        Log.d("favResponse",anError.toString())
                    }
                })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cartTitle: TextView = itemView.findViewById(R.id.tv_nama_cart)
        val cartPrize: TextView = itemView.findViewById(R.id.tv_harga_cart)
        val imgCart: ImageView = itemView.findViewById(R.id.img_cart)
        val imgRemove: ImageView = itemView.findViewById(R.id.icon_remove_cart)
        val tvQty: TextView = itemView.findViewById(R.id.tv_qty)
    }

}