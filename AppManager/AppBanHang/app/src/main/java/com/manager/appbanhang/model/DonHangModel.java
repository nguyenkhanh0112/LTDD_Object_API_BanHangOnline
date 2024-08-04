package com.manager.appbanhang.model;

import java.util.ArrayList;

public class DonHangModel {
    private boolean succes;
    private String message;
    private ArrayList<DonHang> result;

    public DonHangModel(boolean succes, String message, ArrayList<DonHang> result) {
        this.succes = succes;
        this.message = message;
        this.result = result;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DonHang> getResult() {
        return result;
    }

    public void setResult(ArrayList<DonHang> result) {
        this.result = result;
    }
}
