package com.manager.appbanhang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.manager.appbanhang.R;
import com.manager.appbanhang.adapter.SanPhamMoiAdapter;
import com.manager.appbanhang.model.SPMoi;
import com.manager.appbanhang.retrofit.ApiBanHang;
import com.manager.appbanhang.retrofit.RetrofitClient;
import com.manager.appbanhang.utils.Utils;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuanLyActivity extends AppCompatActivity {
    private ImageView imageView_them;
    private Toolbar toolbar_quanly;
    private RecyclerView recyclerView_quanly;
    private SanPhamMoiAdapter adapter;
    private ArrayList<SPMoi> spMois;
    private SPMoi spMoi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ApiBanHang apiBanHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly);
        initView();
        ActionToolBar();
        Onclick();
        getData();

    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar_quanly);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_quanly.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrangChuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = item.getItemId();
        if(position==R.id.action_sua){
            SuaSanPham();
        }else if(position==R.id.action_xoa){
            xoaSanPham();
        }
        return super.onContextItemSelected(item);
    }
    private void Onclick(){
        imageView_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addsp = new Intent(getApplicationContext(), ThemSanPhamActivity.class);
                startActivity(addsp);
            }
        });
    }
    private void initView(){
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        imageView_them = findViewById(R.id.image_them);
        toolbar_quanly = findViewById(R.id.toolbar_quanly);
        recyclerView_quanly = findViewById(R.id.recylerview_quanly);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
//      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_quanly.setLayoutManager(layoutManager);
        recyclerView_quanly.setHasFixedSize(true);
        this.registerForContextMenu(recyclerView_quanly);
    }
    private void getData(){
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        spMoiModel -> {
                            if(spMoiModel.isSucces()){
                                spMois = spMoiModel.getResult();
                                adapter = new SanPhamMoiAdapter(getApplicationContext(),spMois);
                                recyclerView_quanly.setAdapter(adapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, "lỗi"+throwable, Toast.LENGTH_SHORT).show();
                        }
                ));
    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
    private void SuaSanPham(){
        Intent intent = new Intent(QuanLyActivity.this, ThemSanPhamActivity.class);
        spMoi = Utils.spMois.get(0);
        intent.putExtra("sua",spMoi);
        Utils.spMois.clear();
        startActivity(intent);
    }
    private void xoaSanPham(){
        Toast.makeText(this, Utils.spMois.get(0).getTensanpham(), Toast.LENGTH_SHORT).show();
        compositeDisposable.add(apiBanHang.delete(Utils.spMois.get(0).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        messageModel -> {
                            if(messageModel.isSucces()){
                                Toast.makeText(this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                                Utils.spMois.clear();
                                getData();
                            }else{
                                Toast.makeText(this, messageModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            Log.e("error", "xoaSanPham: ",throwable );
                        }
                ));
    }
}
