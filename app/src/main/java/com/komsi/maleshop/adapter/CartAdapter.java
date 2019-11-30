package com.komsi.maleshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.komsi.maleshop.model.Produk;
import com.komsi.maleshop.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Produk> list;
    private TextView hargaProduk;
    private Double hargaTotal = 0.0;


    public CartAdapter(Context context, ArrayList<Produk> list, TextView hargaProduk) {
        this.context = context;
        this.list = list;
        this.hargaProduk = hargaProduk;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getThumbnail()).into(holder.imgCart);
        holder.cartTitle.setText(list.get(position).getNama());
        holder.cartColor.setText(list.get(position).getColour());
        holder.cartPrize.setText(list.get(position).getHarga());
        hargaProduk.setText("Rp " + getHargaTotal());
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyItemRemoved(position);
                hargaProduk.setText("Rp " + getHargaTotal());
                notifyItemRangeChanged(position,list.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView cartTitle;
        private TextView cartColor;
        private TextView cartSize;
        private TextView cartPrize;
        private ImageView imgCart;
        private Button imgRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.img_cart);
            cartTitle = itemView.findViewById(R.id.tv_nama_cart);
            cartSize = itemView.findViewById(R.id.tv_ukuran_cart);
            cartPrize = itemView.findViewById(R.id.tv_harga_cart);
            cartColor = itemView.findViewById(R.id.tv_warna_cart);
            imgRemove = itemView.findViewById(R.id.icon_remove_cart);

        }
    }

    public Double getHargaTotal(){
        hargaTotal = 0.0;
        for (int i=0;i<list.size();i++){
            hargaTotal += Double.parseDouble(list.get(i).getHarga());
        }
        return hargaTotal;

    }
}
