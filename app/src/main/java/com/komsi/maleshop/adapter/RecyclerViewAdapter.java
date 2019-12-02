package com.komsi.maleshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.snackbar.Snackbar;
import com.komsi.maleshop.R;
import com.komsi.maleshop.activity.DetailActivity;
import com.komsi.maleshop.model.Produk;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
     Context mContext;
     List<Produk> mData;


    public RecyclerViewAdapter(Context mContext, List<Produk> mData) {
        this.mContext = mContext;
        this.mData =mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_kaos,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_kaos_merk.setText(mData.get(position).getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("nama", mData.get(position).getNama());
                intent.putExtra("harga", mData.get(position).getHarga());
                intent.putExtra("gambar", mData.get(position).getThumbnail());
                mContext.startActivity(intent);
            }
        });
        Glide.with(mContext).load(mData.get(position).getThumbnail())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.img_kaos);
        holder.tv_kaos_harga.setText(mData.get(position).getHarga());
        holder.img_bookmark.setOnClickListener(new View.OnClickListener() {
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

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_kaos_merk;
        TextView tv_kaos_harga;
        ImageView img_kaos;
        ProgressBar progressBar;
        ImageView img_bookmark;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_kaos_merk=(TextView) itemView.findViewById(R.id.merek_cv_item_kaos);
            tv_kaos_harga=(TextView) itemView.findViewById(R.id.harga_cv_item_kaos);
            img_kaos=(ImageView)itemView.findViewById(R.id.img_cv_item_kaos);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_circular);
            img_bookmark = (ImageView) itemView.findViewById(R.id.img_bookmark);


        }
    }
}
