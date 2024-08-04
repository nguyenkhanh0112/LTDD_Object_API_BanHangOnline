package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangNhapActivity extends AppCompatActivity {
    private EditText edit_email,edit_pass;
    private Button btn_dangnhap;
    private TextView next_fromdangki,txt_quenmatkhau;
    private ApiBanHang apiBanHang;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhxa();
        next_fromdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dangki = new Intent(DangNhapActivity.this,DangKiActivity.class);
                startActivity(dangki);
            }
        });

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangnhap();
            }
        });
        txt_quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhapActivity.this, ResetPassActivity.class);
                startActivity(intent);
            }
        });
    }
    private void anhxa(){
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        edit_email = findViewById(R.id.Email);
        edit_pass = findViewById(R.id.PassWords);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        next_fromdangki = findViewById(R.id.textview_dangki);
        txt_quenmatkhau = findViewById(R.id.quenmatkhau);
    }
    private void Dangnhap(){
        Utils.user.setEmail(edit_email.getText().toString().trim());
        String email =edit_email.getText().toString().trim();
        String pass =edit_pass.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(this, "bạn chưa nhập email", Toast.LENGTH_SHORT).show();
        }else if(pass.isEmpty()){
            Toast.makeText(this, "bạn chưa nhập pass", Toast.LENGTH_SHORT).show();
        }else{
            compositeDisposable.add(apiBanHang.dangnhap(email,pass)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            userModel -> {
                                if(userModel.isSucces()){
                                    Utils.user = userModel.getResult().get(0);
                                        Intent intent = new Intent(getApplicationContext(), TrangChuActivity.class);
                                        startActivity(intent);
                                        finish();
                                }else{
                                    Toast.makeText(this, userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },
                            throwable -> {
                                Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("aaa","sss"+throwable);
                            }
                    ));
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user.getEmail()!=null && Utils.user.getPass()!=null){
            edit_email.setText(Utils.user.getEmail());
            edit_pass.setText(Utils.user.getPass());
        }
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}