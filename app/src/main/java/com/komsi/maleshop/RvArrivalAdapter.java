package com.komsi.maleshop;

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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.merk.setText(mData.get(i).getMerk());
        myViewHolder.harga.setText(mData.get(i).getHarga());
        Glide.with(context).load(mData.get(i).getThumbnail()).into(myViewHolder.imgArrival);
        myViewHolder.imgArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,DetailActivity.class));
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            merk= itemView.findViewById(R.id.item_merk_arrival);
            harga=itemView.findViewById(R.id.item_harga_arrival);
            imgArrival=itemView.findViewById(R.id.imageViewArrival);
        }
    }
}
