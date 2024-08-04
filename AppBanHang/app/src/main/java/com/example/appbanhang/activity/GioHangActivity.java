package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.adapter.GioHangAdapter;
import com.example.appbanhang.model.GioHang;
import com.example.appbanhang.utils.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {
    private TextView giohangtrong,tongtien;
    private Toolbar toolbar_giohang;
    private RecyclerView recyclerView_giohang;
    private Button btmuahang;
    private GioHangAdapter adapter;
    private ArrayList<GioHang>listGioHang;
    private long tongtien1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhXa();
        ActionToolBar();
        TinhTongTien();
        btmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                intent.putExtra("tongtien",tongtien1);
                for (int i = Utils.gioHangList.size() - 1; i >= 0; i--) {
                    for (int j = 0; j < Utils.listmuahang.size(); j++) {
                        if (Utils.listmuahang.get(j).getIdsp() == Utils.gioHangList.get(i).getIdsp()) {
                            Utils.gioHangList.remove(i);
                            break; // Sau khi xóa, thoát khỏi vòng lặp nội bộ.
                        }
                    }
                }
                startActivity(intent);
            }
        });
    }
    public void TinhTongTien(){
        tongtien1 = 0;
        for (int i=0;i<Utils.listmuahang.size();i++){
            tongtien1+=(Utils.listmuahang.get(i).getGiasp()*Utils.listmuahang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText(decimalFormat.format(tongtien1)+"Đ");

    }
    private void anhXa(){
        giohangtrong = findViewById(R.id.giohangtrong);
        tongtien = findViewById(R.id.tongtiengiohang);
        toolbar_giohang = findViewById(R.id.toolbar_giohang);
        recyclerView_giohang = findViewById(R.id.recylerviewgiohang);
        btmuahang = findViewById(R.id.btn_muahang);
    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar_giohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_giohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView_giohang.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_giohang.setLayoutManager(layoutManager);
        if(Utils.gioHangList.size() == 0){
            giohangtrong.setVisibility(View.VISIBLE);
        }else {
            adapter = new GioHangAdapter(this,Utils.gioHangList);
            recyclerView_giohang.setAdapter(adapter);
        }
    }
}