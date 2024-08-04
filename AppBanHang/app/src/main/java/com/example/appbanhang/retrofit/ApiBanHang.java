package com.example.appbanhang.retrofit;

import com.example.appbanhang.model.DonHangModel;
import com.example.appbanhang.model.SPMoiModel;
import com.example.appbanhang.model.User;
import com.example.appbanhang.model.UserModel;
import com.example.appbanhang.model.loaiSPModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiBanHang {
    @GET("GetData.php")
    Observable<loaiSPModel>getLoaiSp();

    @GET("GetDataMoi.php")
    Observable<SPMoiModel>getSpMoi();
    @POST("GetDataChiTiet.php")
    @FormUrlEncoded
    Observable<SPMoiModel>getCTSP(
            @Field("idloaisanpham") int idloaisanpham
//            @Field("page") int page
    );
    @POST("DangKi.php")
    @FormUrlEncoded
    Observable<UserModel>dangki(
            @Field("email") String email,
            @Field("username")String username,
            @Field("pass") String pass,
            @Field("dienthoai")String dienthoai
    );
    @POST("DangNhap.php")
    @FormUrlEncoded
    Observable<UserModel>dangnhap(
            @Field("email") String email,
            @Field("pass") String pass
    );
    @POST("Rest_Pass.php")
    @FormUrlEncoded
    Observable<UserModel>restpass(
            @Field("email") String email,
            @Field("pass") String pass
    );
    @POST("DonHang.php")
    @FormUrlEncoded
    Observable<UserModel>donhang(
            @Field("iduser") int iduser,
            @Field("diachi") String diachi,
            @Field("sdt") String sdt,
            @Field("email") String email,
            @Field("soluong") int soluong,
            @Field("tongtien") String tongtien,
            @Field("chitiet") String chitiet,
            @Field("thoigian") String thoigian
    );

    @POST("XemDonHang.php")
    @FormUrlEncoded
    Observable<DonHangModel>xemdonhang(
            @Field("iduser") int iduser
    );

    @POST("SearchData.php")
    @FormUrlEncoded
    Observable<SPMoiModel>searchdata(
            @Field("search") String search
    );
    @POST("UpdateUser.php")
    @FormUrlEncoded
    Observable<UserModel>updateuser(
            @Field("id") int id,
            @Field("email") String email,
            @Field("username")String username,
            @Field("pass") String pass,
            @Field("sodienthoai")String dienthoai
    );
}
