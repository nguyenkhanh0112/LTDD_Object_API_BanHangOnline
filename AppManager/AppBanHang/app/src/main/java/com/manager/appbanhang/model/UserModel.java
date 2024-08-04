package com.manager.appbanhang.model;

import java.util.ArrayList;

public class UserModel {
    private boolean succes;
    private String message;
    private ArrayList<User> result;

    public UserModel(boolean succes, String message, ArrayList<User> result) {
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

    public ArrayList<User> getResult() {
        return result;
    }

    public void setResult(ArrayList<User> result) {
        this.result = result;
    }
}
