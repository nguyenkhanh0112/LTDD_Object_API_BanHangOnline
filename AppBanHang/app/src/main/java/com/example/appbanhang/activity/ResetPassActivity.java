package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhang.R;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ResetPassActivity extends AppCompatActivity {
    private TextView email,newpass,resetpass;
    private Button btn_capnhap;
    private ApiBanHang apiBanHang;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        anhxa();
        email.setText(Utils.user.getEmail());
        btn_capnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    capnhap();
            }
        });
    }
    private void anhxa(){
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        email = findViewById(R.id.Email);
        newpass = findViewById(R.id.newPassWords);
        resetpass = findViewById(R.id.resetPassWords);
        btn_capnhap = findViewById(R.id.btn_CapNhap);

    }
    private void capnhap(){
        String email1 = email.getText().toString().trim();
        String newpassword = newpass.getText().toString().trim();
        String resetpass1 = resetpass.getText().toString().trim();
        if(email1.isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
            email.requestFocus();
        }else if(newpassword.isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
            newpass.requestFocus();
        }else if(resetpass1.isEmpty()){
            Toast.makeText(this, "hãy nhập lại mật khẩu mới", Toast.LENGTH_SHORT).show();
            resetpass.requestFocus();
        }else{
            if(newpassword.equals(resetpass1)){
                compositeDisposable.add(apiBanHang.restpass(email1,newpassword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSucces()){
                                        Utils.user.setEmail(email1);
                                        Utils.user.setPass(newpassword);
                                        Intent intent = new Intent(getApplicationContext(),DangNhapActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, throwable -> {
                                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.e("aaaa","aa"+throwable);
                                }
                        ));
                    }
                }
            }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}