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
import com.example.appbanhang.adapter.GioHangAdapter;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThanhToanActivity extends AppCompatActivity {
    private Toolbar toolbar_thanhtoan;
    private TextView tongtien,email,sdt,timer;
    private EditText diachi;
    private Button dathang;
    private GioHangActivity gioHangActivity;
    private ApiBanHang apiBanHang;
    private Long tongtien1;
    private  int soluonghang;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        getSoluong();
        AnhXa();
        ActionToolBar();
    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar_thanhtoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_thanhtoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getSoluong(){
        soluonghang =0;
        if (Utils.listmuahang!=null){
            for (int i =0;i<Utils.listmuahang.size();i++){
                soluonghang+= Utils.listmuahang.get(i).getSoluong();
            }
        }
    }
    private void AnhXa() {
        timer = findViewById(R.id.timer123);
        toolbar_thanhtoan = findViewById(R.id.toolbar_thanhtoan);
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        tongtien = findViewById(R.id.tongtien1);
        email = findViewById(R.id.email);
        sdt = findViewById(R.id.sdt);
        diachi = findViewById(R.id.diachi);
        dathang = findViewById(R.id.btn_dathang);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien1 = getIntent().getLongExtra("tongtien",0);
        tongtien.setText(decimalFormat.format(tongtien1)+"Đ");
        sdt.setText(Utils.user.getSodienthoai());
        email.setText(Utils.user.getEmail());
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);
        timer.setText(formattedDate);

        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timer1 = timer.getText().toString().trim();
                String diachi1 = diachi.getText().toString().trim();
                if(Utils.listmuahang.size()==0){
                    Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                    startActivity(intent);
                    Toast.makeText(ThanhToanActivity.this, "Bạn chưa chọn sản phẩm để đặt hàng hãy quay lại và chọn sản phẩm cần mua!", Toast.LENGTH_SHORT).show();
                }
                else if(diachi1.isEmpty()){
                    Toast.makeText(ThanhToanActivity.this, "Bạn chưa nhập địa chỉ!", Toast.LENGTH_SHORT).show();
                    diachi.requestFocus();
                }else{
                    // postData
                    String str_email = Utils.user.getEmail();
                    String str_sdt =Utils.user.getSodienthoai();
                    int iduser = Utils.user.getId();
//                    Gson gson = new Gson();
//                    gson.toJson(Utils.gioHangList);
                    compositeDisposable.add(apiBanHang.donhang(iduser,diachi1,str_sdt,str_email,soluonghang,String.valueOf(tongtien1),new Gson().toJson(Utils.listmuahang),timer1)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userModel -> {
                                        if(userModel.isSucces()){
                                            Toast.makeText(ThanhToanActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                                            Utils.listmuahang.clear();
                                            Intent intent = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    },throwable -> {
                                        Toast.makeText(ThanhToanActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("error", "onClick: " ,throwable);
                                    }
                            ));
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}