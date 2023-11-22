package com.example.datsanbongda.model;

public class DanhGia {
    int maDanhGia;
    String danhGia;
    String tenND;

    public DanhGia(int maDanhGia, String danhGia, String tenND) {
        this.maDanhGia = maDanhGia;
        this.danhGia = danhGia;
        this.tenND = tenND;
    }

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }
}
