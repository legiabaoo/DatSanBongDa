package com.example.datsanbongda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datsanbongda.ActivityChuSan.MainChuSanActivity;
import com.example.datsanbongda.ActivityKhachHang.MainActivity;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.KhachHang;

public class DangNhapActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        Button btnDangKy = findViewById(R.id.btnDangKy);
        EditText edtSoDt = findViewById(R.id.edtSdtDangNhap);
        EditText edtMatKhau = findViewById(R.id.edtMatkhauDangNhap);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = edtSoDt.getText().toString();
                String matkhau = edtMatKhau.getText().toString();
                DbHelper dbHelper = new DbHelper(DangNhapActivity.this);

//                if (sdt.equals("0123456789") && matkhau.equals("admin")) {
//                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(DangNhapActivity.this, MainChuSanActivity.class));
//                } else if (sdt.isEmpty() || matkhau.isEmpty()) {
//                    Toast.makeText(DangNhapActivity.this, "Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
//                }else{
//                    KhachHang khachHang = dbHelper.DangNhap(sdt,matkhau);
//                    if(khachHang != null)
//                    {
//                        Toast.makeText(DangNhapActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("soDienThoai",khachHang.getSoDienThoai());
//                        editor.putString("tenkh",khachHang.getTenKhachHang());
//                        editor.apply();
//                        startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
//                    }else {
//                        Toast.makeText(DangNhapActivity.this, "Tài Khoản Và Mật Khẩu Không Tồn Tại", Toast.LENGTH_SHORT).show();
//                    }
//                }
                //test nhanh
                if(sdt.equals("1")){
                    startActivity(new Intent(DangNhapActivity.this, MainChuSanActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhapActivity.this, DangKiActivity.class));
            }
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

    }
}