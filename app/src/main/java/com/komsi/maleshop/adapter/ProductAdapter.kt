package com.komsi.maleshop.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.bumptech.glide.Glide
import com.komsi.maleshop.R
import com.komsi.maleshop.model.Produk
import com.komsi.maleshop.repository.local.Credential
import com.komsi.maleshop.utils.ConstApi
import java.util.*

class ProductAdapter(var context: Context, val callback: ProductCallback) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    val list = ArrayList<Produk>()

    fun setData(item: ArrayList<Produk>) {
        this.list.clear()
        this.list.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return list[position].id.toLong()
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.merk.text = list[position].nama
        myViewHolder.harga.text = list[position].harga.toString()
        Glide.with(context).load(ConstApi.PRODUCT_IMAGE_URL.value + list[position].foto).into(myViewHolder.imgArrival)
        myViewHolder.itemView.setOnClickListener {
            callback.productOnClick(list[position])
            Log.d("adapterr", "itemview clicked")
        }
        if (list[position].isFavorited) {
            Glide.with(context)
                    .load(R.drawable.ic_favorite_black_24dp)
                    .into(myViewHolder.btnBookmark)
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_favorite_white_24dp)
                    .into(myViewHolder.btnBookmark)
        }
        myViewHolder.btnBookmark.setOnClickListener {
            favButtonClicked(list[position].id.toString(), position,list[position].isFavorited)
        }
    }

    private fun favButtonClicked(id: String, position: Int, method:Boolean) {
        AndroidNetworking.initialize(context)
        var fan = AndroidNetworking.post(ConstApi.WISHLIST.value)
                if (method){
                    fan = AndroidNetworking.delete(ConstApi.WISHLIST.value + "/$id")
                }

                fan
                .addHeaders("Authorization", "Bearer ${Credential.getToken(context)}")
                .addBodyParameter("produk_id", id)
                .setTag("favorite")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(object : StringRequestListener{
                    override fun onResponse(response: String?) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        list[position].isFavorited = !list[position].isFavorited
                        notifyItemChanged(position)
                        callback.onFavoriteClick()
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

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val merk: TextView = itemView.findViewById(R.id.item_merk_arrival) as TextView
        val harga: TextView = itemView.findViewById(R.id.item_harga_arrival) as TextView
        val imgArrival: ImageView = itemView.findViewById(R.id.imageViewArrival) as ImageView
        val cardView: CardView = itemView.findViewById(R.id.cardView) as CardView
        val btnBookmark: ImageButton = itemView.findViewById(R.id.btn_bookmark) as ImageButton

    }

}

interface ProductCallback {
    fun productOnClick(produk: Produk)
    fun onFavoriteClick()
}
