package com.example.datsanbongda.model;

public class San5Home {
    String tenSan;
    int trangThai, loaiSan;

    public San5Home(String tenSan, int trangThai, int loaiSan) {
        this.tenSan = tenSan;
        this.trangThai = trangThai;
        this.loaiSan = loaiSan;
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
