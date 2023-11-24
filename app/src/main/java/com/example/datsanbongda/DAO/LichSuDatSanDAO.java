package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DanhGia;
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

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DATCHO", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new LichSuDatSan(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7)));
            } while (cursor.moveToNext());

        }
        return list;
    }
    public boolean themLichSu(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("thoiGianBatDau", "14:00");
        contentValues.put("thoiGianKetThuc", "16:00");
        contentValues.put("ngay", "24/11/2023");
        contentValues.put("trangthaiDatCho", 1);
        contentValues.put("maSan", 1);
        contentValues.put("maKhachHang", 1);
        contentValues.put("maChuSan", 1);
        long check = sqLiteDatabase.insert("DATCHO", null, contentValues);
        return check!=-1;
    }
}
