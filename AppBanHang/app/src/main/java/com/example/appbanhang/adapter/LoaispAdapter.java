package com.example.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.model.loaiSP;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<loaiSP> listsp;

    public LoaispAdapter(Context context, int layout, ArrayList<loaiSP> listsp) {
        this.context = context;
        this.layout = layout;
        this.listsp = listsp;
    }

    @Override
    public int getCount() {
        return listsp.size();
    }

    @Override
    public Object getItem(int i) {
        return listsp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class ViewHolder{
        ImageView imageView_item;
        TextView textView_tensp;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.imageView_item = view.findViewById(R.id.item_image);
            holder.textView_tensp = view.findViewById(R.id.item_tenSP);
            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();
        loaiSP loaisp = listsp.get(i);
        holder.textView_tensp.setText(loaisp.getTensanpham());
        Glide.with(context).load(loaisp.getHinhanhsanpham()).into(holder.imageView_item);
        return  view;
    }
}
