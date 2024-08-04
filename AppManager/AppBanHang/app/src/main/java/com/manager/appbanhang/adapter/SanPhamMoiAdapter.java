package com.manager.appbanhang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manager.appbanhang.Interface.ItemClickListener;
import com.manager.appbanhang.R;
import com.manager.appbanhang.activity.ChiTietSPActivity;
import com.manager.appbanhang.model.SPMoi;
import com.manager.appbanhang.utils.Utils;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SPMoi sanphammoi = spMoiArrayList.get(position);
        holder.txtTen.setText(sanphammoi.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtGia.setText("Giá: "+ decimalFormat.format(Double.parseDouble(sanphammoi.getGiasanpham()))+" Đ");
        Glide.with(context).load(sanphammoi.getHinhanhsanpham()).into(holder.imageViewSP);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if (!isLongClick) {
                    Intent intent = new Intent(context, ChiTietSPActivity.class);
                    intent.putExtra("data", sanphammoi);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }else{
                    Utils.spMois.add(sanphammoi);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return spMoiArrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnLongClickListener, View.OnClickListener {
        TextView txtGia ,txtTen;
        ImageView imageViewSP;
        private ItemClickListener itemClickListener;
        public MyViewHolder(View itemView){
            super(itemView);
            txtGia = itemView.findViewById(R.id.giasp_moi);
            txtTen = itemView.findViewById(R.id.tensp_moi);
            imageViewSP = itemView.findViewById(R.id.image_spmoi);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(0,R.id.action_sua,getAdapterPosition(),"Sửa");
            contextMenu.add(0,R.id.action_xoa,getAdapterPosition(),"Xóa");
        }
        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true);
            view.showContextMenu();
            return true;
        }
    }

}
