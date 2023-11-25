package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LichSuDatSan;

import java.util.ArrayList;

public class LichSuDatSanDAO {
    DbHelper dbHelper;
    public LichSuDatSanDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LichSuDatSan> getDSLichSu() {
        ArrayList<LichSuDatSan> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DATCHO.*, SAN.tenSan, KHACHHANG.tenKhachHang FROM DATCHO " +
                "INNER JOIN SAN ON DATCHO.maSan = SAN.maSan " +
                "INNER JOIN KHACHHANG ON DATCHO.maKhachHang = KHACHHANG.maKhachHang", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new LichSuDatSan(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9)));
            } while (cursor.moveToNext());

        }
        return list;
    }
    public boolean themLichSu(LichSuDatSan lichSuDatSan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("thoiGianBatDau", lichSuDatSan.getThoiGianBatDau());
        contentValues.put("thoiGianKetThuc", lichSuDatSan.getThoiGianKetThuc());
        contentValues.put("ngay", lichSuDatSan.getNgay());
        contentValues.put("trangthaiDatCho", lichSuDatSan.getTrangThai());
        contentValues.put("maSan", lichSuDatSan.getMaSan());
        contentValues.put("maKhachHang", lichSuDatSan.getMaKhachHang());
        contentValues.put("maChuSan", lichSuDatSan.getMaChuSan());
        long check = sqLiteDatabase.insert("DATCHO", null, contentValues);
        return check!=-1;
    }
}
