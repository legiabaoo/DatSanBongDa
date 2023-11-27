package com.example.datsanbongda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LoaiSan;

public class ThongTinSanDAO {
    private final DbHelper dbHelper;

    public ThongTinSanDAO(Context context) {
dbHelper=new DbHelper(context);
    }
    public boolean updata(LoaiSan loaiSan){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("tienSanSang", String.valueOf(loaiSan.getTienSanSang()));
        cv.put("tienSanToi",String.valueOf(loaiSan.getTienSanToi()));

        long check = database.update("LOAISAN", cv, "maLoaiSan=?",
                new String[]{String.valueOf(loaiSan.getMaLoaiSan())});
        return check != -1 ;
    }
}
