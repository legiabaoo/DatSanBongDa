package com.example.datsanbongda.model;

public class DoanhThu {
    int maVe, trangThai, maSan, maKhachHang, maChuSan, tienSan;
    String ngay, thoiGianBatDau, thoiGianKetThuc, tenSan, tenKhachHang, ngayDat;
    public DoanhThu(String thoiGianBatDau, String thoiGianKetThuc, String ngay, String ngayDat, int tienSan,int trangThai, int maSan, int maKhachHang, int maChuSan) {
        this.maVe = maVe;
        this.trangThai = trangThai;
        this.maSan = maSan;
        this.maKhachHang = maKhachHang;
        this.maChuSan = maChuSan;
        this.ngay = ngay;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.ngayDat = ngayDat;
        this.tienSan = tienSan;
    }
    public DoanhThu(int maVe, String thoiGianBatDau, String thoiGianKetThuc, String ngay, String ngayDat, int tienSan,int trangThai, int maSan, int maKhachHang, int maChuSan, String tenSan, String tenKhachHang) {
        this.maVe = maVe;
        this.trangThai = trangThai;
        this.maSan = maSan;
        this.maKhachHang = maKhachHang;
        this.maChuSan = maChuSan;
        this.ngay = ngay;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.tenSan = tenSan;
        this.tenKhachHang = tenKhachHang;
        this.ngayDat = ngayDat;
        this.tienSan = tienSan;
    }


    public int getTienSan() {
        return tienSan;
    }

    public void setTienSan(int tienSan) {
        this.tienSan = tienSan;
    }

    public DoanhThu(int maVe, int trangThai) {
        this.maVe = maVe;
        this.trangThai = trangThai;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getMaVe() {
        return maVe;
    }

    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaSan() {
        return maSan;
    }

    public void setMaSan(int maSan) {
        this.maSan = maSan;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public int getMaChuSan() {
        return maChuSan;
    }

    public void setMaChuSan(int maChuSan) {
        this.maChuSan = maChuSan;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(String thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public String getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(String thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
}
