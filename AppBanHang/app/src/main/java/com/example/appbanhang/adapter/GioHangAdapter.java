package com.example.appbanhang.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.Interface.ImageClickListenner;
import com.example.appbanhang.R;
import com.example.appbanhang.activity.GioHangActivity;
import com.example.appbanhang.model.GioHang;
import com.example.appbanhang.utils.Utils;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    private GioHangActivity context;
    private List<GioHang> gioHangList;

    public GioHangAdapter(GioHangActivity context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }
    @NonNull
    @Override
    public GioHangAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sp_giohang,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.MyViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.tensp.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText(decimalFormat.format(gioHang.getGiasp())+"Đ");
        holder.soluongsp.setText(String.valueOf(gioHang.getSoluong()));
        Glide.with(context).load(gioHang.getHinhsp()).into(holder.hinhanhsp);
        long tongtien = gioHang.getGiasp() * gioHang.getSoluong();
        holder.tongtiencua1sp.setText(decimalFormat.format(tongtien)+"Đ");
            holder.checkBox_sp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        Utils.listmuahang.add(gioHang);
                        context.TinhTongTien();
                    }else{
                        for (int i=0;i<Utils.listmuahang.size();i++){
                            if(Utils.listmuahang.get(i).getIdsp() == gioHang.getIdsp()){
                                Utils.listmuahang.remove(i);
                            }
                        }
                        context.TinhTongTien();
                    }
                }
            });
        holder.setImageClickListenner(new ImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if(giatri==1){
                    if(gioHangList.get(pos).getSoluong()>1){
                        int soluong = gioHangList.get(pos).getSoluong()-1;
                        gioHangList.get(pos).setSoluong(soluong);
                        Utils.gioHangList.get(pos).setSoluong(soluong);
                        holder.soluongsp.setText(String.valueOf(gioHangList.get(pos).getSoluong()));
                        long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        holder.tongtiencua1sp.setText(decimalFormat.format(gia)+"Đ");
                        context.TinhTongTien();
                        notifyDataSetChanged();
                    }else if(gioHangList.get(pos).getSoluong()==1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Thông Báo");
                        builder.setMessage("Bạn có muốn xóa sản phẩm "+gioHangList.get(pos).getTensp()+" này khỏi giỏ hàng ?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.gioHangList.remove(pos);
                                notifyDataSetChanged();
                                context.TinhTongTien();
                                Toast.makeText(context, "Xóa sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    }

                }else if(giatri==2){
                    if(gioHangList.get(pos).getSoluong()<11){
                        int soluong = gioHangList.get(pos).getSoluong()+1;
                        gioHangList.get(pos).setSoluong(soluong);
                        Utils.gioHangList.get(pos).setSoluong(soluong);
                    }
                    holder.soluongsp.setText(String.valueOf(gioHangList.get(pos).getSoluong()));
                    long gia = gioHangList.get(pos).getSoluong() * gioHangList.get(pos).getGiasp();
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    holder.tongtiencua1sp.setText(decimalFormat.format(gia)+"Đ");
                    notifyDataSetChanged();
                    context.TinhTongTien();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tensp ,giasp,soluongsp,tongtiencua1sp;
        ImageView hinhanhsp , addsp,subsp;
        ImageClickListenner imageClickListenner;
        CheckBox checkBox_sp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox_sp = itemView.findViewById(R.id.check_box_giohang);
            tensp = itemView.findViewById(R.id.ten_sp_giohang);
            giasp = itemView.findViewById(R.id.gia_tien);
            soluongsp = itemView.findViewById(R.id.soluongsp);
            hinhanhsp = itemView.findViewById(R.id.image_spgiohang);
            addsp = itemView.findViewById(R.id.cong);
            subsp = itemView.findViewById(R.id.tru);
            tongtiencua1sp = itemView.findViewById(R.id.tongtien_cua_1_sp);
            addsp.setOnClickListener(this);
            subsp.setOnClickListener(this);
        }
        public void setImageClickListenner(ImageClickListenner imageClickListenner) {
            this.imageClickListenner = imageClickListenner;
        }

        @Override
        public void onClick(View view) {
            if(view == addsp){
                imageClickListenner.onImageClick(view,getAdapterPosition(),2);
            }else if(view == subsp){
                imageClickListenner.onImageClick(view,getAdapterPosition(),1);
            }
        }
    }
}
