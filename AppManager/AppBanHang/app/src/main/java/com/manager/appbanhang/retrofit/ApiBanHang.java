package com.manager.appbanhang.retrofit;

import com.manager.appbanhang.model.DonHangModel;
import com.manager.appbanhang.model.MessageModel;
import com.manager.appbanhang.model.SPMoiModel;
import com.manager.appbanhang.model.UserModel;
import com.manager.appbanhang.model.loaiSPModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    @POST("Insert.php")
    @FormUrlEncoded
    Observable<MessageModel>insert(
            @Field("tensanpham") String tensanpham,
            @Field("giasanpham") String giasanpham,
            @Field("hinhanhsanpham") String hinhanhsanpham,
            @Field("motasanpham") String motasanpham,
            @Field("idloaisanpham") int idloaisanpham
    );
    @POST("Delete.php")
    @FormUrlEncoded
    Observable<MessageModel>delete(
            @Field("id") int idsanpham
    );
    @POST("Update.php")
    @FormUrlEncoded
    Observable<MessageModel>update(
            @Field("id") int idsanpham,
            @Field("tensanpham") String tensanpham,
            @Field("giasanpham") String giasanpham,
            @Field("hinhanhsanpham") String hinhanhsanpham,
            @Field("motasanpham") String motasanpham,
            @Field("idloaisanpham") int idloaisanpham
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
