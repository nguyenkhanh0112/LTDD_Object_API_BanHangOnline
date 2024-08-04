package com.example.appbanhang.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.DonHang;

import java.util.ArrayList;
import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool recycledViewPool= new RecyclerView.RecycledViewPool();
    private Context context;
    private List<DonHang> donHangs;

    public DonHangAdapter(Context context, List<DonHang> donHangs) {
        this.context = context;
        this.donHangs = donHangs;
    }

    @NonNull
    @Override
    public DonHangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_donhang,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.MyViewHolder holder, int position) {
        DonHang donHang = donHangs.get(position);
        holder.idDonhang.setText(donHang.getId()+"");
        holder.thoigiandathang.setText(donHang.getThoigian());
        holder.diachidathang.setText(donHang.getDiachi());
        LinearLayoutManager manager = new LinearLayoutManager(holder.recyclerView_chitietdonhang.getContext(),LinearLayoutManager.VERTICAL,false);
        manager.setInitialPrefetchItemCount(donHang.getItem().size());
        //adapter chi tiet
        AdapterChitiet chitiet = new AdapterChitiet(context, donHang.getItem());
        holder.recyclerView_chitietdonhang.setLayoutManager(manager);
        holder.recyclerView_chitietdonhang.setAdapter(chitiet);
        holder.recyclerView_chitietdonhang.setRecycledViewPool(recycledViewPool);
  }
    @Override
    public int getItemCount() {
        return donHangs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView idDonhang,thoigiandathang,diachidathang;
        RecyclerView recyclerView_chitietdonhang;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            idDonhang = itemView.findViewById(R.id.iddonhang);
            thoigiandathang = itemView.findViewById(R.id.thoigiandathang);
            diachidathang = itemView.findViewById(R.id.diachidathang);
            recyclerView_chitietdonhang = itemView.findViewById(R.id.recylerview_chitietdonhang);
        }
    }
}
