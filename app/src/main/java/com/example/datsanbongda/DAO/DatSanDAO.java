package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DoanhThu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatSanDAO {
    private DbHelper dbHelper;

    public DatSanDAO(Context context) {
        this.dbHelper = new DbHelper(context);
    }
    public ArrayList<DoanhThu> getDSGio(String tngay, String tenSan) {
        ArrayList<DoanhThu> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        // Chuyển đổi định dạng ngày
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        try {
            Date date = inputFormat.parse(tngay);
            tngay = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT DOANHTHU.*, SAN.tenSan, KHACHHANG.tenKhachHang " +
                        "FROM DOANHTHU " +
                        "INNER JOIN SAN ON DOANHTHU.maSan = SAN.maSan " +
                        "INNER JOIN KHACHHANG ON DOANHTHU.maKhachHang = KHACHHANG.maKhachHang " +
                        "WHERE date(REPLACE(SUBSTR(ngay, 7, 4) || '-' || SUBSTR(ngay, 4, 2) || '-' || SUBSTR(ngay, 1, 2), '/', '-')) = date(?) "+
                        "AND tenSan =?"
                , new String[]{tngay, tenSan});

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
        return list;
    }
    public boolean kiemTraDatSan(String tngay, String tenSan, String thoiGianBatDau, String thoiGianKetThuc){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        boolean flag;
        // Chuyển đổi định dạng ngày
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        try {
            Date date = inputFormat.parse(tngay);
            tngay = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Cursor cursor = null;
        Cursor cursor1 = null;
        Cursor cursor2 = null;
        try {
            cursor = sqLiteDatabase.rawQuery(
                    "SELECT DOANHTHU.*, SAN.tenSan, KHACHHANG.tenKhachHang " +
                            "FROM DOANHTHU " +
                            "INNER JOIN SAN ON DOANHTHU.maSan = SAN.maSan " +
                            "INNER JOIN KHACHHANG ON DOANHTHU.maKhachHang = KHACHHANG.maKhachHang " +
                            "WHERE " +
                            "date(REPLACE(SUBSTR(ngay, 7, 4) || '-' || SUBSTR(ngay, 4, 2) || '-' || SUBSTR(ngay, 1, 2), '/', '-')) = date(?) " +
                            "AND tenSan =? " +
                            "AND datetime(thoiGianBatDau)=datetime(?) AND datetime(thoiGianKetThuc)=datetime(?)",
                    new String[]{tngay, tenSan, thoiGianBatDau, thoiGianKetThuc});
            cursor1 = sqLiteDatabase.rawQuery(
                    "SELECT DOANHTHU.*, SAN.tenSan, KHACHHANG.tenKhachHang " +
                            "FROM DOANHTHU " +
                            "INNER JOIN SAN ON DOANHTHU.maSan = SAN.maSan " +
                            "INNER JOIN KHACHHANG ON DOANHTHU.maKhachHang = KHACHHANG.maKhachHang " +
                            "WHERE " +
                            "date(REPLACE(SUBSTR(ngay, 7, 4) || '-' || SUBSTR(ngay, 4, 2) || '-' || SUBSTR(ngay, 1, 2), '/', '-')) = date(?) " +
                            "AND tenSan =? " +
                            "AND (datetime(thoiGianBatDau)<=datetime(?) AND datetime(?)<=datetime(thoiGianKetThuc) OR " +
                            "datetime(thoiGianBatDau)<=datetime(?) AND datetime(?)<=datetime(thoiGianKetThuc))",
                    new String[]{tngay, tenSan, thoiGianBatDau, thoiGianBatDau, thoiGianKetThuc, thoiGianKetThuc});
            cursor2 = sqLiteDatabase.rawQuery(
                    "SELECT DOANHTHU.*, SAN.tenSan, KHACHHANG.tenKhachHang " +
                            "FROM DOANHTHU " +
                            "INNER JOIN SAN ON DOANHTHU.maSan = SAN.maSan " +
                            "INNER JOIN KHACHHANG ON DOANHTHU.maKhachHang = KHACHHANG.maKhachHang " +
                            "WHERE " +
                            "date(REPLACE(SUBSTR(ngay, 7, 4) || '-' || SUBSTR(ngay, 4, 2) || '-' || SUBSTR(ngay, 1, 2), '/', '-')) = date(?) " +
                            "AND tenSan =? " +
                            "AND datetime(?)<=datetime(thoiGianBatDau) AND " +
                            "datetime(?)>=datetime(thoiGianKetThuc)",
                    new String[]{tngay, tenSan, thoiGianBatDau, thoiGianKetThuc});
            // Kiểm tra số lượng bản ghi trong Cursor
            if (cursor != null && cursor.getCount() > 0) {
                flag = false; // Có dữ liệu, trùng
            } else {
                if (cursor1 != null && cursor1.getCount() > 0){
                    flag = false;
                }else if(cursor2 != null && cursor2.getCount() > 0){
                    flag = false;
                }else{
                    flag = true;
                }
            }
        } finally {
            // Đảm bảo đóng Cursor
            if (cursor != null) {
                cursor.close();
            }
        }

        return flag;
    }
    public void taoMaThanhToan(String soDienThoai, int maVe){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("noiDung", soDienThoai);
        contentValues.put("maVe", maVe);
        sqLiteDatabase.insert("MATHANHTOAN", null, contentValues);
    }
}
