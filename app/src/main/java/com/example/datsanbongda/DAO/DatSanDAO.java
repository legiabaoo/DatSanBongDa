package com.example.datsanbongda.DAO;

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
                , new String[]{tngay, String.valueOf(tenSan)});

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


}
