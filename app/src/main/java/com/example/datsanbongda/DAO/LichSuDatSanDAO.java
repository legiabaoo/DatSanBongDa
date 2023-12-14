package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LichSuDatSan;
import com.example.datsanbongda.model.LichSuDuyetSan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class LichSuDatSanDAO {
    DbHelper dbHelper;
    public LichSuDatSanDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<LichSuDatSan> getDSLichSuGiamDan() {
        ArrayList<LichSuDatSan> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DATCHO.*, SAN.tenSan, KHACHHANG.tenKhachHang FROM DATCHO " +
                "INNER JOIN SAN ON DATCHO.maSan = SAN.maSan " +
                "INNER JOIN KHACHHANG ON DATCHO.maKhachHang = KHACHHANG.maKhachHang " +
                "ORDER BY DATCHO.maVe DESC", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new LichSuDatSan(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        cursor.getString(9),
                        cursor.getString(10)));
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
        contentValues.put("ngayDat", lichSuDatSan.getNgayDat());
        long check = sqLiteDatabase.insert("DATCHO", null, contentValues);
        return check!=-1;
    }
    public boolean ThayDoiTrangThai(LichSuDatSan lichSuDatSan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangThaiDatCho", lichSuDatSan.getTrangThai());
        int check = sqLiteDatabase.update("DATCHO", contentValues, "maVe=?", new String[]{String.valueOf(lichSuDatSan.getMaVe())});
        return check!=0;
    }
    public boolean deleteLichSu(int maVe){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("DATCHO", "maVe=?",
                new String []{String.valueOf(maVe)});
        return check != -1 ;
    }
}
