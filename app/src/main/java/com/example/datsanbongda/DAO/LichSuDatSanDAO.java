package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LichSuDatSan;

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
        Collections.sort(list, new Comparator<LichSuDatSan>() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            @Override
            public int compare(LichSuDatSan o1, LichSuDatSan o2) {
                try {
                    Date date1 = simpleDateFormat.parse(o1.getNgay());
                    Date date2 = simpleDateFormat.parse(o2.getNgay());
                    return date2.compareTo(date1);
                }catch (ParseException e){
                    e.printStackTrace();
                    return 0;
                }
            }
        });
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
