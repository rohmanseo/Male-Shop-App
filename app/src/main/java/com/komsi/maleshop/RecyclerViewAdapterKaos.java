package com.komsi.maleshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapterKaos extends RecyclerView.Adapter<RecyclerViewAdapterKaos.MyViewHolder> {
    private Context mContext;
    private List<KaosKategori> mData;


    public RecyclerViewAdapterKaos(Context mcontext, List<KaosKategori> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_kaos,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_kaos_merk.setText(mData.get(position).getMerk());
        holder.img_kaos.setImageResource(mData.get(position).getThumbnail());
        holder.tv_kaos_harga.setText(mData.get(position).getHarga());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class  MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_kaos_merk;
        TextView tv_kaos_harga;
        ImageView img_kaos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_kaos_merk=(TextView) itemView.findViewById(R.id.merek_cv_item_kaos);
            tv_kaos_harga=(TextView) itemView.findViewById(R.id.harga_cv_item_kaos);
            img_kaos=(ImageView)itemView.findViewById(R.id.img_cv_item_kaos);


        }
    }
}
