package com.example.appbanhang.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.appbanhang.R;
import com.example.appbanhang.adapter.LoaispAdapter;
import com.example.appbanhang.adapter.SanPhamMoiAdapter;
import com.example.appbanhang.model.SPMoi;
import com.example.appbanhang.model.loaiSP;
import com.example.appbanhang.retrofit.ApiBanHang;
import com.example.appbanhang.retrofit.RetrofitClient;
import com.example.appbanhang.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TrangChuActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerView_Main;
    private NavigationView navigationView;
    private ListView listView_Main;
    private DrawerLayout drawerLayout;
    private LoaispAdapter adapter;
    private ArrayList<loaiSP> listSP;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ApiBanHang apiBanHang;
    private NotificationBadge badge;
    private FrameLayout frameLayout;
    private ArrayList<SPMoi>listSPMoi;
    private ImageView imageView_search;
    private SanPhamMoiAdapter sanPhamMoiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        apiBanHang = RetrofitClient.getInstance(Utils.Base_Url).create(ApiBanHang.class);
        AnhXa();
        ActionBar();
        ActionViewFlipper();
        getLoaiSanPham();
        getSPMoi();
        getEvenClick();
    }
    private void getEvenClick(){
        listView_Main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:{
                        Intent trangchu = new Intent(getApplicationContext(), TrangChuActivity.class);
                        startActivity(trangchu);
                        break;
                    } case 1:{
                        Intent dienthoai = new Intent(getApplicationContext(),DienThoaiActivity.class);
                        dienthoai.putExtra("loai",1);
                        startActivity(dienthoai);
                        break;
                    } case 2:{
                        Intent laptop = new Intent(getApplicationContext(), LaptopActivity.class);
                        laptop.putExtra("loai",2);
                        startActivity(laptop);
                        break;
                    } case 3:{
                        Intent thongtinuser = new Intent(getApplicationContext(), ThongTinActivity.class);
                        startActivity(thongtinuser);
                        break;
                    }
                    case 4:{
                        Intent xemdonhang = new Intent(getApplicationContext(), XemDonHangActivity.class);
                        startActivity(xemdonhang);
                        break;
                    } case 5:{
                        Intent dangxuat = new Intent(getApplicationContext(), DangNhapActivity.class);
                        startActivity(dangxuat);
                        finish();
                        break;
                    }
                }
            }
        });
    }
    private void getLoaiSanPham(){
       compositeDisposable.add(apiBanHang.getLoaiSp()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                      loaiSPModel -> {
                          if(loaiSPModel.isSucces()){
                              listSP =loaiSPModel.getResult();
                              listSP.add(new loaiSP("Đăng Xuất","https://cdn-icons-png.flaticon.com/128/6568/6568636.png"));
                              adapter = new LoaispAdapter(getApplicationContext(),R.layout.dong_chucnangvasanpham,listSP);
                              listView_Main.setAdapter(adapter);
                          }
                      }
               ));
    }
    private void getSPMoi(){
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        spMoiModel -> {
                            if(spMoiModel.isSucces()){
                                listSPMoi = spMoiModel.getResult();
                                sanPhamMoiAdapter = new SanPhamMoiAdapter(getApplicationContext(),listSPMoi);
                                recyclerView_Main.setAdapter(sanPhamMoiAdapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được với sever"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }
    private void ActionBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void ActionViewFlipper(){
        ArrayList<String> listQC = new ArrayList<>();
        listQC.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        listQC.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        listQC.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for(int i=0;i<listQC.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(listQC.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
    private void AnhXa(){
        imageView_search = findViewById(R.id.search);
        toolbar = (Toolbar) findViewById(R.id.toolbar_Main);
        FrameLayout frameLayout1 = findViewById(R.id.framelayout1);
        badge = findViewById(R.id.menu_sl1);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewfilpper);
        recyclerView_Main = (RecyclerView) findViewById(R.id.recylerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView_Main.setLayoutManager(layoutManager);
        recyclerView_Main.setHasFixedSize(true);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        listView_Main = (ListView) findViewById(R.id.listview_Main);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerlayout);
        listSP = new ArrayList<>();
        listSPMoi = new ArrayList<>();
        if(Utils.gioHangList ==null){
            Utils.gioHangList = new ArrayList<>();
        }else{
            int totalItem =0;
            for (int i=0;i<Utils.gioHangList.size();i++){
                totalItem+=Utils.gioHangList.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        frameLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });

        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrangChuActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        int totalItem =0;
        for (int i=0;i<Utils.gioHangList.size();i++){
            totalItem+=Utils.gioHangList.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }
}