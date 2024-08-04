package com.manager.appbanhang.model;

public class User {
    private int id;
    private String email;
    private String pass;
    private String username;
    private String sodienthoai;

    public User() {
    }

    public User(int id, String email, String pass, String username, String sodienthoai) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.username = username;
        this.sodienthoai = sodienthoai;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }
}
