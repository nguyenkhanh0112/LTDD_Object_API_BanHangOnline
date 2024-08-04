package com.manager.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.manager.appbanhang.R;
import com.manager.appbanhang.adapter.DonHangAdapter;
import com.manager.appbanhang.model.DonHang;
import com.manager.appbanhang.retrofit.ApiBanHang;
import com.manager.appbanhang.retrofit.RetrofitClient;
import com.manager.appbanhang.utils.Utils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class XemDonHangActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ApiBanHang apiBanHang;
    private RecyclerView recyclerView_xemdonhang;
    private DonHangAdapter donHangAdapter;
    private Toolbar toolbar_donhang;
    private ArrayList<DonHang> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_don_hang);
        anhxa();
        ActionBar();
        GetOrder();
    }
    private  void GetOrder(){
        compositeDisposable.add(apiBanHang.xemdonhang(Utils.user.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donHangModel -> {
                            if(donHangModel.isSucces()){
                                arrayList = donHangModel.getResult();
                                donHangAdapter = new DonHangAdapter(XemDonHangActivity.this,arrayList);
                                recyclerView_xemdonhang.setAdapter(donHangAdapter);
                            }
                        },throwable -> {
                            Toast.makeText(this, "lỗi"+throwable, Toast.LENGTH_SHORT).show();
                            Log.e("aaa", "error: ",throwable );
                        }
                ));

    }
    private void ActionBar(){
        setSupportActionBar(toolbar_donhang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_donhang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void anhxa(){
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        recyclerView_xemdonhang = findViewById(R.id.recylerview_donhang);
        toolbar_donhang =findViewById(R.id.toolbar_donhang);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView_xemdonhang.setLayoutManager(manager);
        arrayList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}