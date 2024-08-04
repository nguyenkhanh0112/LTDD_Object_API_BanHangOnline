package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.model.GioHang;
import com.example.appbanhang.model.SPMoi;
import com.example.appbanhang.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSPActivity extends AppCompatActivity {
    private TextView ten_sp,gia_sp,mota_sp;
    private Spinner spinner;
    private Button btn_them;
    private ImageView hinhanh;
    private Toolbar toolbar_chitietsp;
    private SPMoi spMoi;
    private NotificationBadge badge;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_spactivity);
        initView();
        ActionToolBar();
        initData();
        Btn_click_themsp();

    }
    private void initData(){
        spMoi = (SPMoi) getIntent().getSerializableExtra("data");
        ten_sp.setText(spMoi.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        gia_sp.setText("Giá: "+decimalFormat.format(Double.parseDouble(spMoi.getGiasanpham().trim()))+"Đ");
        mota_sp.setText(spMoi.getMotasanpham());
        Glide.with(this).load(spMoi.getHinhanhsanpham()).into(hinhanh);
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(arrayAdapter);
    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar_chitietsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_chitietsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initView(){
        frameLayout = findViewById(R.id.framelayout);
        ten_sp = findViewById(R.id.ten_chitietsp);
        gia_sp = findViewById(R.id.gia_chitietsp);
        mota_sp = findViewById(R.id.mo_ta_chi_tiet_sp);
        spinner = (Spinner)findViewById(R.id.spinner_chitiet_sp);
        hinhanh = (ImageView) findViewById(R.id.image_chitietsp);
        btn_them =(Button) findViewById(R.id.them_sp);
        toolbar_chitietsp = findViewById(R.id.toolbar_chitietsp);
        badge = (NotificationBadge) findViewById(R.id.menu_sl);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });

        int totalItem=0;
        if (Utils.gioHangList!=null){
            for (int i =0;i<Utils.gioHangList.size();i++){
                totalItem+= Utils.gioHangList.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
    private void Btn_click_themsp(){
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themsp();
            }
        });
    }
    private void themsp(){
        if(Utils.gioHangList.size()>0){
            boolean flag =false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i=0;i<Utils.gioHangList.size();i++) {
                if (Utils.gioHangList.get(i).getIdsp() == spMoi.getId()) {
                    Utils.gioHangList.get(i).setSoluong(soluong + Utils.gioHangList.get(i).getSoluong());
                    long gia = Long.parseLong(spMoi.getGiasanpham());
                    Utils.gioHangList.get(i).setGiasp(gia);
                    flag = true;
                }
            }
                if(flag==false){
                    long gia = Long.parseLong(spMoi.getGiasanpham()) * soluong;
                    GioHang gioHang = new GioHang(spMoi.getId(),spMoi.getTensanpham(),gia,spMoi.getHinhanhsanpham(),soluong);
                    Utils.gioHangList.add(gioHang);
            }
        }else{
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = Long.parseLong(spMoi.getGiasanpham());
            GioHang gioHang = new GioHang(spMoi.getId(),spMoi.getTensanpham(),gia,spMoi.getHinhanhsanpham(),soluong);
            Utils.gioHangList.add(gioHang);
        }
        int totalItem =0;
        if (Utils.gioHangList!=null){
            for (int i =0;i<Utils.gioHangList.size();i++){
                totalItem+= Utils.gioHangList.get(i).getSoluong();
            }
        }
        badge.setText(String.valueOf(totalItem));
    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem=0;
        if (Utils.gioHangList!=null){
            for (int i =0;i<Utils.gioHangList.size();i++){
                totalItem+= Utils.gioHangList.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
}