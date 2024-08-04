package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appbanhang.R;
import com.example.appbanhang.adapter.SanPhamAdapter;
import com.example.appbanhang.model.SPMoi;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LaptopActivity extends AppCompatActivity {
   private androidx.appcompat.widget.Toolbar toolbar_laptop;
   private RecyclerView recyclerView_laptop;
   private CompositeDisposable compositeDisposable =new CompositeDisposable();
   private SanPhamAdapter lapTopAdapter;
   private ArrayList<SPMoi> list_sp_Laptop;
    private ApiBanHang apiBanHang;
    private int loai;
//    int page =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        loai = getIntent().getIntExtra("loai",2);
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        AnhXa();
        ActionToolBar();
        GetData();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void GetData(){
        compositeDisposable.add(apiBanHang.getCTSP(loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    spMoiModel -> {
                        if(spMoiModel.isSucces()){
                            list_sp_Laptop = spMoiModel.getResult();
                            lapTopAdapter = new SanPhamAdapter(getApplicationContext(),list_sp_Laptop);
                            recyclerView_laptop.setAdapter(lapTopAdapter);
                        }
                    }
                ));
    }
    private void AnhXa(){
        toolbar_laptop = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar_laptop);
        recyclerView_laptop =(RecyclerView) findViewById(R.id.recylerview_laptop);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView_laptop.setLayoutManager(manager);
        recyclerView_laptop.setHasFixedSize(true);
        list_sp_Laptop = new ArrayList<>();
    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar_laptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_laptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}