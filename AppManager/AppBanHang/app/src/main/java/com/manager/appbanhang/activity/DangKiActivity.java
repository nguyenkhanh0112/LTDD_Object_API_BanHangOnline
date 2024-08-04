package com.manager.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.appbanhang.R;
import com.manager.appbanhang.retrofit.ApiBanHang;
import com.manager.appbanhang.retrofit.RetrofitClient;
import com.manager.appbanhang.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangKiActivity extends AppCompatActivity {
    private EditText email_danki,pass_dangki,repass,usenname,mobile;
    private Button btn_dangki;
    private ApiBanHang apiBanHang;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        mobile = findViewById(R.id.mobile);
        email_danki = findViewById(R.id.Emaildangki);
        pass_dangki = findViewById(R.id.PassWordsdangki);
        usenname = findViewById(R.id.name);
        repass = findViewById(R.id.repass);
        btn_dangki = findViewById(R.id.btn_dangki);
        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertNguoiDung();
            }
        });
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
    }
    private void insertNguoiDung(){
        String email = email_danki.getText().toString().trim();
        String pass = pass_dangki.getText().toString().trim();
        String name = usenname.getText().toString().trim();
        String mobile1 =mobile.getText().toString().trim();
        String repass1 =repass.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
        }else if(pass.isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập pass", Toast.LENGTH_SHORT).show();
        }else if(mobile1.isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập sdt", Toast.LENGTH_SHORT).show();
        }else if(name.isEmpty()){
            Toast.makeText(this, "Bạn chưa nhập tên ", Toast.LENGTH_SHORT).show();
        }else{
            if(pass.equals(repass1)){
                compositeDisposable.add(apiBanHang.dangki(email,name,pass,mobile1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSucces()){
                                        Utils.user.setEmail(email);
                                        Utils.user.setPass(pass);
                                        Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(this, userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }else{
                Toast.makeText(this, "Pass không đúng! hãy nhập lại", Toast.LENGTH_SHORT).show();
                repass.setText("");
                repass.requestFocus();
            }
        }
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}