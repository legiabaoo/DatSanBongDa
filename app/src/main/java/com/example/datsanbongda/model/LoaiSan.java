package com.example.datsanbongda.model;

public class LoaiSan {
    private int maLoaiSan;
    private String loaiSan;
    private int tienSanSang;
    private int tienSanToi;

    public LoaiSan(int maLoaiSan, String loaiSan, int tienSanSang, int tienSanToi) {
        this.maLoaiSan = maLoaiSan;
        this.loaiSan = loaiSan;
        this.tienSanSang = tienSanSang;
        this.tienSanToi = tienSanToi;
    }

    public LoaiSan() {
    }

    public int getMaLoaiSan() {
        return maLoaiSan;
    }

    public void setMaLoaiSan(int maLoaiSan) {
        this.maLoaiSan = maLoaiSan;
    }

    public String getLoaiSan() {
        return loaiSan;
    }

    public void setLoaiSan(String loaiSan) {
        this.loaiSan = loaiSan;
    }

    public int getTienSanSang() {
        return tienSanSang;
    }

    public void setTienSanSang(int tienSanSang) {
        this.tienSanSang = tienSanSang;
    }

    public int getTienSanToi() {
        return tienSanToi;
    }

    public void setTienSanToi(int tienSanToi) {
        this.tienSanToi = tienSanToi;
    }
}
