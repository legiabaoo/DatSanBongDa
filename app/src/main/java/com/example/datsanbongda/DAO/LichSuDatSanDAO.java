package com.example.datsanbongda.DAO;

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
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getString(3),
                        cursor.getString(1),
                        cursor.getString(2)));
            } while (cursor.moveToNext());

        }
        // tôi đã từng ở đây
        return list;
    }
}
