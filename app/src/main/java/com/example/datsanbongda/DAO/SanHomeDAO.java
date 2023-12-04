package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.San5Home;
import com.example.datsanbongda.model.San7Home;

import java.util.ArrayList;

public class SanHomeDAO {
    private DbHelper dbHelper;
    public SanHomeDAO(Context context){
        this.dbHelper = new DbHelper(context);
    }
    public ArrayList<San7Home> getDSSan7(){
        ArrayList<San7Home> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SAN WHERE maLoaiSan = 1", null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new San7Home(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<San5Home> getDSSan5(){
        ArrayList<San5Home> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SAN WHERE maLoaiSan = 2", null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                list.add(new San5Home(cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean updataSan(San7Home san){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tenSan", san.getTenSan());
        cv.put("trangThaiSan",san.getTrangThai());
        long check = database.update("SAN", cv, "maSan=?",
                new String[]{String.valueOf(san.getMaSan())});
        return check != -1 ;
    }
}
