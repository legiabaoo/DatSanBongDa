package com.example.datsanbongda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "DATSANBONGDA", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tLoaiSan = "CREATE TABLE LOAISAN(maLoaiSan integer primary key, loaiSan text, tienSanSang integer, tienSanToi integer)";
        db.execSQL(tLoaiSan);
        db.execSQL("INSERT INTO LOAISAN VALUES(1, 'Sân 7', 300000, 450000), (2, 'Sân 5', 150000, 300000)");

        String tChuSan = "CREATE TABLE CHUSAN(maChuSan integer primary key, taiKhoan text, matKhau text, soDienThoai text, facebook text)";
        db.execSQL(tChuSan);
        db.execSQL("INSERT INTO CHUSAN VALUES(1, 'phungthaiduong123', '12345678', '0987654321', 'https://www.facebook.com/thaiduong.phung.102')");

        String tKhachHang = "CREATE TABLE KHACHHANG(maKhachHang integer primary key autoincrement, tenKhachHang text, soDienThoai text, matKhau text)";
        db.execSQL(tKhachHang);
        db.execSQL("INSERT INTO KHACHHANG VALUES(1, 'Lê Gia Bảo', '0933145378', '12345678')");

        String tSan = "CREATE TABLE SAN(maSan integer primary key autoincrement, tenSan text, trangThaiSan integer, maLoaiSan integer references LOAISAN(maLoaiSan))";
        db.execSQL(tSan);
        /*
        Trang thai
        0: hoat dong
        1: khong hoat dong
        */
        db.execSQL("INSERT INTO SAN VALUES(1, 'Sân số 1', 0, 2), (2, 'Sân số 2', 0, 2), (3, 'Sân số 3', 0, 2), " +
                "(4, 'Sân số 4', 0, 2), (5, 'Sân số 5', 1, 1)");

        String tDanhGia = "CREATE TABLE DANHGIA(maDanhGia integer primary key autoincrement, tenND text, danhGia text)";
        db.execSQL(tDanhGia);
        db.execSQL("INSERT INTO DANHGIA VALUES(1,'Lê Gia Bảo', 'Sân đẹp'), (2,'Phùng Thái Dương' ,'Phục vụ tốt')," +
                "(3, 'Nguyễn Hoàng Phúc', 'ok'), (4, 'Trương Quốc Trung', 'Tuyệt vời')");

        String tDatCho = "CREATE TABLE DATCHO(maVe integer primary key autoincrement, thoiGianBatDau text, thoiGianKetThuc text, ngay text, trangThaiDatCho integer ," +
                "maSan integer references SAN(maSan), maKhachHang integer references KHACHHANG(maKhachHang), maChuSan integer references CHUSAN(maChuSan))";
        db.execSQL(tDatCho);
        /*
        Trang thai
        2: that bai
        0: cho xac nhan
        1: thanh cong
        */
        db.execSQL("INSERT INTO DATCHO VALUES(1, '16:00', '17:30', '1/12/2023', 1, 1, 1, 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS DANHGIA");
            db.execSQL("DROP TABLE IF EXISTS DATCHO");
            db.execSQL("DROP TABLE IF EXISTS SAN");
            db.execSQL("DROP TABLE IF EXISTS CHUSAN");
            db.execSQL("DROP TABLE IF EXISTS LOAISAN");
            db.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            onCreate(db);
        }
    }
}
