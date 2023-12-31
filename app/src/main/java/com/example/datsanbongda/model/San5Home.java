package com.example.datsanbongda.model;

public class San5Home {
    int maSan;
    String tenSan;
    int trangThai, loaiSan;

    public San5Home(int maSan, String tenSan, int trangThai, int loaiSan) {
        this.maSan = maSan;
        this.tenSan = tenSan;
        this.trangThai = trangThai;
        this.loaiSan = loaiSan;
    }

    public San5Home(int maSan, String tenSan, int trangThai) {
        this.maSan = maSan;
        this.tenSan = tenSan;
        this.trangThai = trangThai;
    }

    public San5Home() {
    }

    public int getMaSan() {
        return maSan;
    }

    public void setMaSan(int maSan) {
        this.maSan = maSan;
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
}
