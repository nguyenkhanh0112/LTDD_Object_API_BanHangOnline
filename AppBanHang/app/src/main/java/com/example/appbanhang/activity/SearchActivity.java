package com.example.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SanPhamAdapter adapter;
    private ArrayList<SPMoi> arrayList;
    private Toolbar toolbar;
    private EditText search;
    private ApiBanHang apiBanHang;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Anhxa();
        ActionToolBar();
    }

    private void Anhxa(){
        search = findViewById(R.id.edit_search);
        apiBanHang= RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        recyclerView =findViewById(R.id.recylerview_search);
        toolbar = findViewById(R.id.toolbar_Search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2khanh) {
                if(charSequence.length()==0){
                    arrayList.clear();
                    adapter= new SanPhamAdapter(getApplicationContext(),arrayList);
                    recyclerView.setAdapter(adapter);
                }else{
                    searchData(charSequence.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void searchData(String s){
        arrayList.clear();
        compositeDisposable.add(apiBanHang.searchdata(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        spMoiModel -> {
                            if (spMoiModel.isSucces()){
                                arrayList = spMoiModel.getResult();
                                adapter = new SanPhamAdapter(getApplicationContext(),arrayList);
                                recyclerView.setAdapter(adapter);
                            }
                        },throwable -> {
                            Toast.makeText(this, throwable +"", Toast.LENGTH_SHORT).show();
                            Log.e("error", "searchData: ",throwable );
                        }
                ));
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
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}