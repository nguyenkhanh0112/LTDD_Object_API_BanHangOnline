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

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SPMoi> spMoiArrayList;

    public SanPhamMoiAdapter(Context context, ArrayList<SPMoi> spMoiArrayList) {
        this.context = context;
        this.spMoiArrayList = spMoiArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sp_moi,parent,false);
        return new MyViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SPMoi sanphammoi = spMoiArrayList.get(position);
        holder.txtTen.setText(sanphammoi.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtGia.setText("Giá: "+ decimalFormat.format(Double.parseDouble(sanphammoi.getGiasanpham()))+" Đ");
        Glide.with(context).load(sanphammoi.getHinhanhsanpham()).into(holder.imageViewSP);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                Intent intent = new Intent(context, ChiTietSPActivity.class);
                intent.putExtra("data",sanphammoi);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return spMoiArrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtGia ,txtTen;
        ImageView imageViewSP;
        private ItemClickListener itemClickListener;
        public MyViewHolder(View itemView){
            super(itemView);
            txtGia = itemView.findViewById(R.id.giasp_moi);
            txtTen = itemView.findViewById(R.id.tensp_moi);
            imageViewSP = itemView.findViewById(R.id.image_spmoi);
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
