package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DoanhThu;
import com.example.datsanbongda.model.LichSuDatSan;
import com.example.datsanbongda.model.LichSuDuyetSan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class DoanhThuDAO {
    DbHelper dbHelper;

    public DoanhThuDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<DoanhThu> getDSDoanhThu(String ngayBD, String ngayKT) {
        ArrayList<DoanhThu> list = new ArrayList<>();
        if(ngayBD.equals("Chọn ngày")&&ngayKT.equals("Chọn ngày")){
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "SELECT DOANHTHU.*, SAN.tenSan, KHACHHANG.tenKhachHang " +
                            "FROM DOANHTHU " +
                            "INNER JOIN SAN ON DOANHTHU.maSan = SAN.maSan " +
                            "INNER JOIN KHACHHANG ON DOANHTHU.maKhachHang = KHACHHANG.maKhachHang "+
                            "ORDER BY DOANHTHU.maVe DESC", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new DoanhThu(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5),
                            cursor.getInt(6),
                            cursor.getInt(7),
                            cursor.getInt(8),
                            cursor.getInt(9),
                            cursor.getString(10),
                            cursor.getString(11)));
                } while (cursor.moveToNext());
            }
        }else{
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(
                    "SELECT DOANHTHU.*, SAN.tenSan, KHACHHANG.tenKhachHang " +
                            "FROM DOANHTHU " +
                            "INNER JOIN SAN ON DOANHTHU.maSan = SAN.maSan " +
                            "INNER JOIN KHACHHANG ON DOANHTHU.maKhachHang = KHACHHANG.maKhachHang " +
                            "WHERE date(REPLACE(SUBSTR(ngayDat, 7, 4) || '-' || SUBSTR(ngayDat, 4, 2) || '-' || SUBSTR(ngayDat, 1, 2), '/', '-')) " +
                            "BETWEEN date(REPLACE(SUBSTR(?, 7, 4) || '-' || SUBSTR(?, 4, 2) || '-' || SUBSTR(?, 1, 2), '/', '-')) " +
                            "AND date(REPLACE(SUBSTR(?, 7, 4) || '-' || SUBSTR(?, 4, 2) || '-' || SUBSTR(?, 1, 2), '/', '-')) "
                    + "ORDER BY DOANHTHU.maVe DESC",
                    new String[]{ngayBD, ngayBD, ngayBD, ngayKT, ngayKT, ngayKT});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new DoanhThu(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getInt(5),
                            cursor.getInt(6),
                            cursor.getInt(7),
                            cursor.getInt(8),
                            cursor.getInt(9),
                            cursor.getString(10),
                            cursor.getString(11)));
                } while (cursor.moveToNext());
            }
        }

        return list;
    }

    public boolean themDoanhThu(DoanhThu doanhThu) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("thoiGianBatDau", doanhThu.getThoiGianBatDau());
        contentValues.put("thoiGianKetThuc", doanhThu.getThoiGianKetThuc());
        contentValues.put("ngay", doanhThu.getNgay());
        contentValues.put("trangThaiDatCho", doanhThu.getTrangThai());
        contentValues.put("maSan", doanhThu.getMaSan());
        contentValues.put("maKhachHang", doanhThu.getMaKhachHang());
        contentValues.put("maChuSan", doanhThu.getMaChuSan());
        contentValues.put("ngayDat", doanhThu.getNgayDat());
        contentValues.put("tienDoanhThu", doanhThu.getTienSan());
        long check = sqLiteDatabase.insert("DOANHTHU", null, contentValues);
        return check != -1;
    }

    public int tongDoanhThu(String ngayBD, String ngayKT) {
        int tongDoanhThuCoc = 0, tongDoanhThu=0;
        if(ngayBD.equals("Chọn ngày")&&ngayKT.equals("Chọn ngày")){
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT tienDoanhThu from DOANHTHU " +
                    "WHERE trangThaiDatCho=3", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    tongDoanhThu += cursor.getInt(0);
                } while (cursor.moveToNext());
            }
            Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT tienDoanhThu from DOANHTHU " +
                    "WHERE trangThaiDatCho=1", null);
            if(cursor1.getCount()>0){
                cursor1.moveToFirst();
                do{
                    tongDoanhThuCoc += 150000;
                } while (cursor.moveToNext());
            }
        }else{
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT tienDoanhThu from DOANHTHU WHERE date(REPLACE(SUBSTR(ngayDat, 7, 4) || '-' || SUBSTR(ngayDat, 4, 2) || '-' || SUBSTR(ngayDat, 1, 2), '/', '-'))" +
                            "BETWEEN date(REPLACE(SUBSTR(?, 7, 4) || '-' || SUBSTR(?, 4, 2) || '-' || SUBSTR(?, 1, 2), '/', '-'))" +
                            "AND date(REPLACE(SUBSTR(?, 7, 4) || '-' || SUBSTR(?, 4, 2) || '-' || SUBSTR(?, 1, 2), '/', '-')) "
                    , new String[]{ngayBD, ngayBD, ngayBD, ngayKT, ngayKT, ngayKT});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    tongDoanhThu += cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return tongDoanhThuCoc+tongDoanhThu;
    }
    public boolean updateDoanhThu(int maVe, int trangThai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangThaiDatCho", trangThai);
        int check = sqLiteDatabase.update("DOANHTHU", contentValues, "maVe=?", new String[]{String.valueOf(maVe)});
        return check!=0;
    }
}
