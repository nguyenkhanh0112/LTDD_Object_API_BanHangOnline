package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class SuaThongTinActivity extends AppCompatActivity {
    private EditText edit_email,edit_ten,edit_sodienthoai,edit_matkhau;
    private Toolbar toolbar_suaThongTin;
    private Button btn_Capnhap;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ApiBanHang apiBanHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);
        anhxa();
        Actiontoolbar();
        disPlay();
    }
    private void anhxa(){
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        edit_email = findViewById(R.id.edit_emailNguoidung);
        edit_ten = findViewById(R.id.edit_tennguoidung);
        edit_matkhau = findViewById(R.id.edit_matkhaunguoidung);
        edit_sodienthoai = findViewById(R.id.edit_sodienthoainguoidung);
        toolbar_suaThongTin =findViewById(R.id.toolbar_suathongtin);
        btn_Capnhap = findViewById(R.id.btn_capnhap);
    }
    private void Actiontoolbar(){
        setSupportActionBar(toolbar_suaThongTin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_suaThongTin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void disPlay(){
        edit_email.setText(Utils.user.getEmail());
        edit_matkhau.setText(Utils.user.getPass());
        edit_ten.setText(Utils.user.getUsername());
        edit_sodienthoai.setText(Utils.user.getSodienthoai());
        btn_Capnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }
    private void updateUser(){
        String email1 =edit_email.getText().toString().trim();
        String matkahu =edit_matkhau.getText().toString().trim();
        String ten1 =edit_ten.getText().toString().trim();
        String sdt1 = edit_sodienthoai.getText().toString().trim();
        compositeDisposable.add(apiBanHang.updateuser(Utils.user.getId(),email1,ten1,matkahu,sdt1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if(userModel.isSucces()){
                                Toast.makeText(this, "Sửa Thành công", Toast.LENGTH_SHORT).show();
                                Utils.user.setEmail(email1);
                                Utils.user.setPass(matkahu);
                                Utils.user.setSodienthoai(sdt1);
                                Utils.user.setUsername(ten1);
                                Intent intent = new Intent(getApplicationContext(), TrangChuActivity.class);
                                startActivity(intent);
                            }
                        },throwable -> {
                            Log.e("Error", "updateUser: ",throwable);
                        }
                ));
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}