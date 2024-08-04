package com.example.appbanhang.model;

public class loaiSP {
    private int id;
    private String tensanpham;
    private String hinhanhsanpham;

    public loaiSP(String tensanpham, String hinhanhsanpham) {
        this.tensanpham = tensanpham;
        this.hinhanhsanpham = hinhanhsanpham;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTensanpham() {
        return tensanpham;
    }
    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }
    public String getHinhanhsanpham() {
        return hinhanhsanpham;
    }
    public void setHinhanhsanpham(String hinhanhsanpham) {
        this.hinhanhsanpham = hinhanhsanpham;
    }
}
