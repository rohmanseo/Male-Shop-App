package com.komsi.maleshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.komsi.maleshop.R;
import com.komsi.maleshop.activity.DetailActivity;
import com.komsi.maleshop.model.Produk;

import java.util.List;

public class RvArrivalAdapter extends RecyclerView.Adapter<RvArrivalAdapter.MyViewHolder> {
   Context context;
   List<Produk> mData;

    public RvArrivalAdapter(Context context, List<Produk> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_rvarrival,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.merk.setText(mData.get(i).getNama());
        myViewHolder.harga.setText(mData.get(i).getHarga());
        Glide.with(context).load(mData.get(i).getThumbnail()).into(myViewHolder.imgArrival);
        myViewHolder.imgArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("nama", mData.get(i).getNama());
                intent.putExtra("harga", mData.get(i).getHarga());
                context.startActivity(intent);
            }
        });
        myViewHolder.btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Dihapus dari favorite",Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView merk;
        private TextView harga;
        private ImageView imgArrival;
        ImageView btn_bookmark;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            merk= itemView.findViewById(R.id.item_merk_arrival);
            harga=itemView.findViewById(R.id.item_harga_arrival);
            imgArrival=itemView.findViewById(R.id.imageViewArrival);
            btn_bookmark = itemView.findViewById(R.id.btn_bookmark);
        }
    }
}
