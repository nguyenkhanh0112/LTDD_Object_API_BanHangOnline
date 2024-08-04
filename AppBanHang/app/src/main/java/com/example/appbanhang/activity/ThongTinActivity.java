package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appbanhang.R;
import com.example.appbanhang.utils.Utils;

public class ThongTinActivity extends AppCompatActivity {
    private Toolbar toolbar_thongtin;
    private TextView email,ten,matkhau,sdt;
    private Button btn_sua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);
        anhxa();
        Actiontoolbar();
        disPlay();
    }
    private void Actiontoolbar(){
        setSupportActionBar(toolbar_thongtin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_thongtin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void anhxa(){
        email = findViewById(R.id.emailNguoidung);
        ten =findViewById(R.id.tennguoidung);
        matkhau  = findViewById(R.id.matkhaunguoidung);
        sdt = findViewById(R.id.sodienthoainguoidung);
        toolbar_thongtin = findViewById(R.id.toolbar_thongtin);
        btn_sua= findViewById(R.id.btn_suaThongtin);
    }
    private void disPlay(){
        email.setText(Utils.user.getEmail().toString());
        ten.setText(Utils.user.getUsername().toString());
        matkhau.setText(Utils.user.getPass().toString());
        sdt.setText(Utils.user.getSodienthoai().toString());
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent suaThongTinUser = new Intent(getApplicationContext(), SuaThongTinActivity.class);
                startActivity(suaThongTinUser);
                finish();
            }
        });
    }
}