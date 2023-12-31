package com.example.datsanbongda.ActivityChuSan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ThongTinSanChuSanActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private ArrayList<LoaiSan> list;
    private ThongTinSanDAO thongTinSanDAO;
    private String loaiSann;
    private int tienSan7Sang, tienSan7Toi, tienSan5Sang, tienSan5Toi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_san_chu_san);
        ImageView imgBack = findViewById(R.id.ImgBackthongtin);
        TextView txtGiaSangSan5 = findViewById(R.id.txtGiaSan5Sang);
        TextView txtGiaSangSan7 = findViewById(R.id.txtGiaSan7Sang);
        TextView txtGiaToiSan5 = findViewById(R.id.txtGiaSan5Toi);
        TextView txtGiaToiSan7 = findViewById(R.id.txtGiaSan7Toi);

        Button btnChinhSuaSan5 = findViewById(R.id.btnChinhSuaThongtinSan5);
        Button btnChinhSuaSan7 = findViewById(R.id.btnChinhSuaThongtinSan7);

        dbHelper = new DbHelper(this);
        thongTinSanDAO = new ThongTinSanDAO(ThongTinSanChuSanActivity.this);
        list = new ArrayList<>();
        // sân 7
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=1", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            loaiSann = cursor.getString(1);
            tienSan7Sang = cursor.getInt(2);
            tienSan7Toi = cursor.getInt(3);
        }
        int tienSanSang1 = tienSan7Sang;
        String tienSanSang2 = dinhdangtien(tienSanSang1);

        int tienSanToi1 = tienSan7Toi;
        String tienSanToi2 = dinhdangtien(tienSanToi1);
        txtGiaSangSan7.setText(String.valueOf(tienSanSang2));
        txtGiaToiSan7.setText(String.valueOf(tienSanToi2));

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
                edtGiaSanSang.setText(String.valueOf(tienSan7Sang));
                edtGiaSanToi.setText(String.valueOf(tienSan7Toi));
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
                                tienSan7Sang = cursor.getInt(2);
                                tienSan7Toi = cursor.getInt(3);

                                tienSanSang1 = tienSan7Sang;
                                String tienSanSang2 = dinhdangtien(tienSanSang1);

                                tienSanToi1 = tienSan7Toi;
                                String tienSanToi2 = dinhdangtien(tienSanToi1);
                                txtGiaSangSan7.setText(String.valueOf(tienSanSang2));
                                txtGiaToiSan7.setText(String.valueOf(tienSanToi2));
                            }

                            Toast.makeText(ThongTinSanChuSanActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(ThongTinSanChuSanActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
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
        // sân 5

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=2", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            loaiSann = cursor.getString(1);
            tienSan5Sang = cursor.getInt(2);
            tienSan5Toi = cursor.getInt(3);
        }
        int tiensansang = tienSan5Sang;
        String tiensansang1 = dinhdangtien(tiensansang);
        int tiensantoi = tienSan5Toi;
        String tiensantoi1 = dinhdangtien(tiensantoi);
        txtGiaSangSan5.setText(String.valueOf(tiensansang1));

        txtGiaToiSan5.setText(String.valueOf(tiensantoi1));
        btnChinhSuaSan5.setOnClickListener(new View.OnClickListener() {
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
                edtGiaSanSang.setText(String.valueOf(tienSan5Sang));
                edtGiaSanToi.setText(String.valueOf(tienSan5Toi));
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tienSanSang1 = Integer.parseInt(edtGiaSanSang.getText().toString());
                        int tienSanToi1 = Integer.parseInt(edtGiaSanToi.getText().toString());
                        int maLoaiSan = 2;
                        LoaiSan loaiSan = new LoaiSan(maLoaiSan, loaiSann, tienSanSang1, tienSanToi1);
                        boolean check = thongTinSanDAO.updata(loaiSan);
                        if (check) {
                            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=2", null);
                            if (cursor.getCount() > 0) {
                                cursor.moveToFirst();
                                loaiSann = cursor.getString(1);
                                tienSan5Sang = cursor.getInt(2);
                                tienSan5Toi = cursor.getInt(3);

                                int tiensansang = tienSan5Sang;
                                String tiensansang1 = dinhdangtien(tiensansang);
                                int tiensantoi = tienSan5Toi;
                                String tiensantoi1 = dinhdangtien(tiensantoi);
                                txtGiaSangSan5.setText(String.valueOf(tiensansang1));

                                txtGiaToiSan5.setText(String.valueOf(tiensantoi1));
                            }

                            Toast.makeText(ThongTinSanChuSanActivity.this, "Thành Công", Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(ThongTinSanChuSanActivity.this, "Thất Bại", Toast.LENGTH_SHORT).show();
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
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorThanhCong));

    }

    private String dinhdangtien(int amount) {
        // Tạo một đối tượng NumberFormat với Locale.getDefault() để định dạng theo ngôn ngữ và quốc gia của thiết bị
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        // Chuyển đổi int thành định dạng tiền tệ và trả về kết quả
        return currencyFormatter.format(amount);
    }
}
