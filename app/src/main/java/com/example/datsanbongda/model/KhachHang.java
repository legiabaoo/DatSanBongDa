package com.example.datsanbongda.model;

public class KhachHang {

    private int maKhachHang;
    private String tenKhachHang;
    private String soDienThoai;
    private String matKhau;

    public KhachHang(){}

    public KhachHang(int maKhachHang, String tenKhachHang, String soDienThoai, String matKhau) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
    }

    public KhachHang(String tenKhachHang, String soDienThoai, String matKhau) {
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
    }

    public KhachHang(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
}
