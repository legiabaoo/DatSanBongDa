package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LichSuDatSan;
import com.example.datsanbongda.model.LichSuDuyetSan;

import java.util.ArrayList;

public class LichSuDuyetSanDAO {
    private DbHelper dbHelper;
    public LichSuDuyetSanDAO(Context context){
        this.dbHelper = new DbHelper(context);
    }
    public ArrayList<LichSuDuyetSan> getDSDuyetSan(){
        ArrayList<LichSuDuyetSan> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT DATCHOCHUSAN.*, SAN.tenSan, KHACHHANG.tenKhachHang FROM DATCHOCHUSAN " +
                "INNER JOIN SAN ON DATCHOCHUSAN.maSan = SAN.maSan " +
                "INNER JOIN KHACHHANG ON KHACHHANG.maKhachHang = DATCHOCHUSAN.maKhachHang " +
                "INNER JOIN MATHANHTOAN ON DATCHOCHUSAN.maVe = MATHANHTOAN.maVe " +
                "WHERE trangThaiDatCho=0",null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new LichSuDuyetSan(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        cursor.getInt(9),
                        cursor.getString(10),
                        cursor.getString(11)));
            } while (cursor.moveToNext());

        }
        return list;
    }
    public boolean themDuyetSan(LichSuDuyetSan lichSuDuyetSan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("thoiGianBatDau", lichSuDuyetSan.getThoiGianBatDau());
        contentValues.put("thoiGianKetThuc", lichSuDuyetSan.getThoiGianKetThuc());
        contentValues.put("ngay", lichSuDuyetSan.getNgay());
        contentValues.put("trangthaiDatCho", lichSuDuyetSan.getTrangThai());
        contentValues.put("maSan", lichSuDuyetSan.getMaSan());
        contentValues.put("maKhachHang", lichSuDuyetSan.getMaKhachHang());
        contentValues.put("maChuSan", lichSuDuyetSan.getMaChuSan());
        contentValues.put("ngayDat", lichSuDuyetSan.getNgayDat());
        contentValues.put("maThanhToan", lichSuDuyetSan.getMaThanhToan());
        long check = sqLiteDatabase.insert("DATCHOCHUSAN", null, contentValues);
        return check!=-1;
    }
    public boolean dongY(LichSuDuyetSan lichSuDuyetSan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangThaiDatCho", lichSuDuyetSan.getTrangThai());
        int check = sqLiteDatabase.update("DATCHOCHUSAN", contentValues, "maVe=?", new String[]{String.valueOf(lichSuDuyetSan.getMaVe())});
        return check!=0;
    }
}
