package com.example.datsanbongda.model;

public class MaThanhToan {
    int maThanhToan;
    String noiDung;

    public MaThanhToan(int maThanhToan, String noiDung) {
        this.maThanhToan = maThanhToan;
        this.noiDung = noiDung;
    }

    public int getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
