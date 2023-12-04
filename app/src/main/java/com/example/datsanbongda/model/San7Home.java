package com.example.datsanbongda.model;

public class San7Home {
    int maSan;
    String tenSan;
    int trangThai, loaiSan;

    public San7Home(int maSan, String tenSan, int trangThai, int loaiSan) {
        this.maSan = maSan;
        this.tenSan = tenSan;
        this.trangThai = trangThai;
        this.loaiSan = loaiSan;
    }

    public San7Home() {
    }

    public San7Home(int maSan, String tenSan, int trangThai) {
        this.maSan = maSan;
        this.tenSan = tenSan;
        this.trangThai = trangThai;
    }

    public San7Home(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getLoaiSan() {
        return loaiSan;
    }

    public void setLoaiSan(int loaiSan) {
        this.loaiSan = loaiSan;
    }
    public int getMaSan() {
        return maSan;
    }

    public void setMaSan(int maSan) {
        this.maSan = maSan;
    }
}
