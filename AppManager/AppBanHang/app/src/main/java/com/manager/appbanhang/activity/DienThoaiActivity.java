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
import com.manager.appbanhang.adapter.SanPhamAdapter;
import com.manager.appbanhang.model.SPMoi;
import com.manager.appbanhang.retrofit.ApiBanHang;
import com.manager.appbanhang.retrofit.RetrofitClient;
import com.manager.appbanhang.utils.Utils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DienThoaiActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView_dt;
    private ApiBanHang apiBanHang;
    private SanPhamAdapter dienThoaiAdapter;
    private ArrayList<SPMoi> spMoiList;
    private int loai;
//    int page=1;
    private CompositeDisposable compositeDisposable =new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        loai = getIntent().getIntExtra("loai",1);
        AnhXa();
        ActionToolBar();
        GetData();

    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void AnhXa(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_smart);
        recyclerView_dt =(RecyclerView) findViewById(R.id.recylerview_dt);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView_dt.setLayoutManager(manager);
        recyclerView_dt.setHasFixedSize(true);
        spMoiList = new ArrayList<>();
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
                                spMoiList = spMoiModel.getResult();
                                dienThoaiAdapter = new SanPhamAdapter(getApplicationContext(),spMoiList);
                                recyclerView_dt.setAdapter(dienThoaiAdapter);
                            }
                        },
                        throwable -> {
                            Log.e("aaaa","Không kết nỗi sever",throwable);
                            Toast.makeText(getApplicationContext(), "không kết nối sever", Toast.LENGTH_SHORT).show();
                        }
                ));
    }
}