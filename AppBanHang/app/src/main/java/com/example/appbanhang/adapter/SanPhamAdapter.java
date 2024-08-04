package com.example.appbanhang.adapter;

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
import com.example.appbanhang.Interface.ItemClickListener;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.ChiTietSPActivity;
import com.example.appbanhang.model.SPMoi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.MyViewHolder> {
    Context context;
    ArrayList<SPMoi> arrary;

    public SanPhamAdapter(Context context, List<SPMoi> arrary) {
        this.context = context;
        this.arrary = (ArrayList<SPMoi>) arrary;
    }

    @NonNull
    @Override
    public SanPhamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanpham_dt,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.MyViewHolder holder, int position) {
        SPMoi sanpham = arrary.get(position);
        holder.tensp.setText(sanpham.getTensanpham().trim());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: "+decimalFormat.format(Double.parseDouble(sanpham.getGiasanpham().trim()))+"Đ");
        holder.mota.setText(sanpham.getMotasanpham().trim());
        Glide.with(context).load(sanpham.getHinhanhsanpham()).into(holder.hinhanh);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        Intent intent = new Intent(context, ChiTietSPActivity.class);
                        intent.putExtra("data",sanpham);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrary.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tensp,giasp,mota;
        ImageView hinhanh;
        private ItemClickListener itemClickListener;
        public MyViewHolder(View viewItem){
            super(viewItem);
            tensp= viewItem.findViewById(R.id.ten_sp_dt);
            giasp= viewItem.findViewById(R.id.gia_item_dt);
            mota= viewItem.findViewById(R.id.mota_item_dt);
            hinhanh= viewItem.findViewById(R.id.image_item_dt);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }
    }
}
