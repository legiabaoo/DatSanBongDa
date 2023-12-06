package com.example.datsanbongda.model;

public class LichSuDuyetSan {
    int maVe, trangThai, maSan, maKhachHang, maChuSan;
    String ngay, thoiGianBatDau, thoiGianKetThuc, tenSan, tenKhachHang, ngayDat, maThanhToan;
    public LichSuDuyetSan(String thoiGianBatDau, String thoiGianKetThuc, String ngay, String ngayDat, int trangThai, int maSan, int maKhachHang, int maChuSan, String maThanhToan) {
        this.maVe = maVe;
        this.trangThai = trangThai;
        this.maSan = maSan;
        this.maKhachHang = maKhachHang;
        this.maChuSan = maChuSan;
        this.ngay = ngay;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.ngayDat = ngayDat;
        this.maThanhToan = maThanhToan;
    }
    public LichSuDuyetSan(int maVe, String thoiGianBatDau, String thoiGianKetThuc, String ngay, String ngayDat, int trangThai, String maThanhToan, int maSan, int maKhachHang, int maChuSan, String tenSan, String tenKhachHang) {
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
        this.maThanhToan = maThanhToan;
    }
//    public LichSuDuyetSan(int maVe, String thoiGianBatDau, String thoiGianKetThuc, String ngay, String ngayDat, int trangThai, int maSan, int maKhachHang, int maChuSan, String tenSan, String tenKhachHang) {
//        this.maVe = maVe;
//        this.trangThai = trangThai;
//        this.maSan = maSan;
//        this.maKhachHang = maKhachHang;
//        this.maChuSan = maChuSan;
//        this.ngay = ngay;
//        this.thoiGianBatDau = thoiGianBatDau;
//        this.thoiGianKetThuc = thoiGianKetThuc;
//        this.tenSan = tenSan;
//        this.tenKhachHang = tenKhachHang;
//        this.ngayDat = ngayDat;
//
//    }

    public LichSuDuyetSan(int maVe, int trangThai) {
        this.maVe = maVe;
        this.trangThai = trangThai;
    }

    public String getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(String maThanhToan) {
        this.maThanhToan = maThanhToan;
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
