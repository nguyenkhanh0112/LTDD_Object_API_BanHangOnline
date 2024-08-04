package com.example.appbanhang.model;

import java.util.ArrayList;

public class SPMoiModel {
    private boolean succes;
    private String message;
    private ArrayList<SPMoi> result;

    public SPMoiModel(boolean succes, String message, ArrayList<SPMoi> result) {
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

    public ArrayList<SPMoi> getResult() {
        return result;
    }

    public void setResult(ArrayList<SPMoi> result) {
        this.result = result;
    }
}
