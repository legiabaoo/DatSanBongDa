package com.example.datsanbongda.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DanhGia;

import java.util.ArrayList;

public class DanhGiaDAO {
    DbHelper dbHelper;

    public DanhGiaDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<DanhGia> getDSDanhGia() {
        ArrayList<DanhGia> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DANHGIA", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new DanhGia(cursor.getInt(0),
                        cursor.getString(2),
                        cursor.getString(1)));
            } while (cursor.moveToNext());

        }
        return list;
    }
}
