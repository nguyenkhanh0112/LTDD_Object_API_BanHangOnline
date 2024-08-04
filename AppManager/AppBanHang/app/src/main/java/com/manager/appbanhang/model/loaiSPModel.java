package com.manager.appbanhang.model;

import java.util.ArrayList;

public class loaiSPModel {
    private boolean succes;
    private String message;
    private ArrayList<loaiSP>result;

    public loaiSPModel(boolean succes, String message, ArrayList<loaiSP> result) {
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

    public ArrayList<loaiSP> getResult() {
        return result;
    }

    public void setResult(ArrayList<loaiSP> result) {
        this.result = result;
    }
}
