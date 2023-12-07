package com.example.datsanbongda.ActivityKhachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;

import java.text.NumberFormat;
import java.util.Locale;

public class ThongTinSanActivity extends AppCompatActivity {
    private int tienSan7Sang, tienSan7Toi, tienSan5Sang, tienSan5Toi;
    private String loaiSann;
    private DbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_san);
        ImageView imgBack = findViewById(R.id.ImgBackthongtin);
        TextView txtGiaSangSan5 = findViewById(R.id.txtGiaSan5Sang);
        TextView txtGiaSangSan7 = findViewById(R.id.txtGiaSan7Sang);
        TextView txtGiaToiSan5 = findViewById(R.id.txtGiaSan5Toi);
        TextView txtGiaToiSan7 = findViewById(R.id.txtGiaSan7Toi);

        dbHelper = new DbHelper(this);
        layGiaSan5();
        layGiaSan7();
        txtGiaSangSan5.setText(dinhdangtien(tienSan5Sang));
        txtGiaToiSan5.setText(dinhdangtien(tienSan5Toi));
        txtGiaSangSan7.setText(dinhdangtien(tienSan7Sang));
        txtGiaToiSan7.setText(dinhdangtien(tienSan7Toi));
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorThanhCong));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void layGiaSan7(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            loaiSann = cursor.getString(1);
            tienSan7Sang = cursor.getInt(2);
            tienSan7Toi = cursor.getInt(3);
        }
    }
    private void layGiaSan5(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=2", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            loaiSann = cursor.getString(1);
            tienSan5Sang = cursor.getInt(2);
            tienSan5Toi = cursor.getInt(3);
        }
    }
    private String dinhdangtien(int amount) {
        // Tạo một đối tượng NumberFormat với Locale.getDefault() để định dạng theo ngôn ngữ và quốc gia của thiết bị
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        // Chuyển đổi int thành định dạng tiền tệ và trả về kết quả
        return currencyFormatter.format(amount);
    }
}