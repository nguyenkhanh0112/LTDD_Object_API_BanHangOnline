package com.manager.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manager.appbanhang.R;
import com.manager.appbanhang.model.Item;

import java.util.ArrayList;

public class AdapterChitiet extends RecyclerView.Adapter<AdapterChitiet.MyViewHolder> {
    private Context context;
    private ArrayList<Item> items;

    public AdapterChitiet(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterChitiet.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_chitietdonhang,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChitiet.MyViewHolder holder, int position) {
        Item item = items.get(position);
        holder.txt_tenspchitietdonhang.setText("Tên sản phẩm: "+item.getTensanpham());
        holder.txt_soluongchitietsp.setText("Số lượng: "+item.getSoluong());
        Glide.with(context).load(item.getHinhanhsanpham()).into(holder.imageView);

    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_tenspchitietdonhang,txt_soluongchitietsp;
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgchitiet);
            txt_soluongchitietsp = itemView.findViewById(R.id.soluongchitietsp);
            txt_tenspchitietdonhang=itemView.findViewById(R.id.tenspchitietdonhang);
        }
    }
}
