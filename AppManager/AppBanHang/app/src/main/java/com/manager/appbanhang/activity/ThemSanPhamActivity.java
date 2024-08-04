package com.manager.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.manager.appbanhang.R;
import com.manager.appbanhang.model.SPMoi;
import com.manager.appbanhang.retrofit.ApiBanHang;
import com.manager.appbanhang.retrofit.RetrofitClient;
import com.manager.appbanhang.utils.Utils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThemSanPhamActivity extends AppCompatActivity {
    private EditText tensp,giasp,mota,hinhanh;
    private Toolbar toolbar_themsp;
    private Spinner spinner_loai;
    private Button btn_them;
    private CompositeDisposable compositeDisposable =new CompositeDisposable();
    private ApiBanHang apiBanHang;
    private SPMoi spMoi;
    private Boolean flag = false;
    private int loai =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        anhxa();
        ActionToolBar();
        setSpinner_loai();
        Intent intent = getIntent();
        spMoi = (SPMoi) intent.getSerializableExtra("sua");
//        Toast.makeText(this, spMoi.getTensanpham(), Toast.LENGTH_SHORT).show();
        if(spMoi==null){
            flag =false;
        }else{
            flag = true;
            btn_them.setText("Sửa sản phẩm");
            tensp.setText(spMoi.getTensanpham());
            giasp.setText(spMoi.getGiasanpham());
            mota.setText(spMoi.getMotasanpham());
            hinhanh.setText(spMoi.getHinhanhsanpham());
            spinner_loai.setSelection(spMoi.getIdloaisanpham());
            Toast.makeText(this, spMoi.getIdloaisanpham()+"", Toast.LENGTH_SHORT).show();
        }
        setData();
    }
    private void setSpinner_loai(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Vui lòng chọn loại sản phẩm");
        strings.add("Loai 1");
        strings.add("Loai 2");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,strings);
        spinner_loai.setAdapter(adapter);
        spinner_loai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loai=i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void setData(){
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == false){
                    themsanpham();
                }else{
                    SuaSanPham();
                }
            }
        });

    }
    private void SuaSanPham(){
        String str_ten = tensp.getText().toString().trim();
        String str_gia = giasp.getText().toString().trim();
        String str_mota = mota.getText().toString().trim();
        String str_hinhanh = hinhanh.getText().toString().trim();

        if(str_ten.isEmpty()||str_gia.isEmpty()||str_mota.isEmpty()||str_hinhanh.isEmpty()||loai==0){
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

        }else{
            compositeDisposable.add(apiBanHang.update(spMoi.getId(),str_ten,str_gia,str_hinhanh,str_mota,loai)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel -> {
                                if(messageModel.isSucces()){
                                    Toast.makeText(this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), QuanLyActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },throwable -> {
                                Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("error", "suasanpham: ",throwable);
                            }
                    ));
        }
    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar_themsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_themsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void anhxa(){
        toolbar_themsp = findViewById(R.id.toolbar_Themsp);
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        tensp = findViewById(R.id.edit_tensp);
        giasp = findViewById(R.id.edit_giasp);
        hinhanh = findViewById(R.id.edit_linkhinhanh);
        btn_them = findViewById(R.id.them_spsp);
        mota = findViewById(R.id.edit_mota);
        spinner_loai = findViewById(R.id.spinnerLoai);
    }
    private void themsanpham(){
        String str_ten = tensp.getText().toString().trim();
        String str_gia = giasp.getText().toString().trim();
        String str_mota = mota.getText().toString().trim();
        String str_hinhanh = hinhanh.getText().toString().trim();

        if(str_ten.isEmpty()||str_gia.isEmpty()||str_mota.isEmpty()||str_hinhanh.isEmpty()||loai==0){
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();

        }else{
            compositeDisposable.add(apiBanHang.insert(str_ten,str_gia,str_hinhanh,str_mota,loai)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messageModel -> {
                                if(messageModel.isSucces()){

                                    Toast.makeText(this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), QuanLyActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            },throwable -> {
                                Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("error", "themsanpham: ",throwable);
                            }
                    ));
        }
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}