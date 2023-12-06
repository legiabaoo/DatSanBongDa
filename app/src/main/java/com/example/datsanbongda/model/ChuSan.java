package com.example.datsanbongda.model;

public class ChuSan {
    private int maChuSan;
    private String taiKhoan, matKhau ,soDienThoai, facebook;

    public ChuSan(int maChuSan, String taiKhoan, String matKhau, String soDienThoai, String facebook) {
        this.maChuSan = maChuSan;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.facebook = facebook;
    }

    public ChuSan(String soDienThoai, String facebook) {
        this.soDienThoai = soDienThoai;
        this.facebook = facebook;
    }

    public ChuSan() {
    }

    public int getMaChuSan() {
        return maChuSan;
    }

    public void setMaChuSan(int maChuSan) {
        this.maChuSan = maChuSan;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}
