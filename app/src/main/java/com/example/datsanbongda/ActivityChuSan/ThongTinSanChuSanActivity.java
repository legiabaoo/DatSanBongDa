package com.example.datsanbongda.ActivityChuSan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datsanbongda.ActivityKhachHang.DatSanActivity;
import com.example.datsanbongda.ActivityKhachHang.ThongTinSanActivity;
import com.example.datsanbongda.DAO.ThongTinSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LoaiSan;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ThongTinSanChuSanActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private ArrayList<LoaiSan> list;
    private ThongTinSanDAO thongTinSanDAO;
    private String loaiSann;
    private int tienSanSang;
    private int tienSanToi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_san_chu_san);
        ImageView imgBack = findViewById(R.id.ImgBackthongtin);
        TextView txtGiaSangSan5 = findViewById(R.id.txtGiaSan5Sang);
        TextView txtGiaSangSan7 = findViewById(R.id.txtGiaSan7Sang);
        TextView txtGiaToiSan5 = findViewById(R.id.txtGiaSan5Toi);
        TextView txtGiaToiSan7 = findViewById(R.id.txtGiaSan7Toi);
        TextView txtDatSan7 = findViewById(R.id.txtDatsan7);
        TextView txtDatSan5 = findViewById(R.id.txtDatSan5);
        Button btnChinhSuaSan5 = findViewById(R.id.btnChinhSuaThongtinSan5);
        Button btnChinhSuaSan7 = findViewById(R.id.btnChinhSuaThongtinSan7);

        dbHelper = new DbHelper(this);
        thongTinSanDAO = new ThongTinSanDAO(dbHelper);
        list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            loaiSann = cursor.getString(1);
            tienSanSang = cursor.getInt(2);
            tienSanToi = cursor.getInt(3);
        }
        txtGiaSangSan7.setText(String.valueOf(tienSanSang));
        txtGiaToiSan7.setText(String.valueOf(tienSanToi));

        btnChinhSuaSan7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(ThongTinSanChuSanActivity.this).inflate(R.layout.dialog_chinhsuathongtinsan, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinSanChuSanActivity.this);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                TextInputEditText edtGiaSanSang = view.findViewById(R.id.edtGiaSanSang);
                TextInputEditText edtGiaSanToi = view.findViewById(R.id.edtGiaSanToi);
                Button btnUpdate = view.findViewById(R.id.btnUpdate);
                Button btnCancel = view.findViewById(R.id.btnCancel);
                edtGiaSanSang.setText(String.valueOf(tienSanSang));
                edtGiaSanToi.setText(String.valueOf(tienSanToi));
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tienSanSang1 = Integer.parseInt(edtGiaSanSang.getText().toString());
                        int tienSanToi1 = Integer.parseInt(edtGiaSanToi.getText().toString());
                        int maLoaiSan = 1;
                        LoaiSan loaiSan = new LoaiSan(maLoaiSan, loaiSann, tienSanSang1, tienSanToi1);
                        boolean check = thongTinSanDAO.updata(loaiSan);
                        if (check) {
                            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=1", null);
                            if (cursor.getCount() > 0) {
                                cursor.moveToFirst();
                                loaiSann = cursor.getString(1);
                                tienSanSang = cursor.getInt(2);
                                tienSanToi = cursor.getInt(3);

                                txtGiaSangSan7.setText(String.valueOf(tienSanSang));
                                txtGiaToiSan7.setText(String.valueOf(tienSanToi));
                            }

                            Toast.makeText(ThongTinSanChuSanActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(ThongTinSanChuSanActivity.this, "That Bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }
        });


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtDatSan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongTinSanChuSanActivity.this, DatSanActivity.class));

            }
        });
        txtDatSan7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongTinSanChuSanActivity.this, DatSanActivity.class));

            }
        });
    }
}