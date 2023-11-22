package com.example.datsanbongda.model;

import com.example.datsanbongda.DAO.LichSuDatSanDAO;

public class LichSuDatSan {
    int maVe, trangThai, maSan, maKhachHang, maChuSan;
    String ngay, thoiGianBatDau, thoiGianKetThuc;
    public LichSuDatSan(int maVe, int trangThai, int maSan, int maKhachHang, int maChuSan, String ngay, String thoiGianBatDau, String thoiGianKetThuc) {
        this.maVe = maVe;
        this.trangThai = trangThai;
        this.maSan = maSan;
        this.maKhachHang = maKhachHang;
        this.maChuSan = maChuSan;
        this.ngay = ngay;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
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
}
