package com.example.datsanbongda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datsanbongda.FragmantKhachHang.CaNhanFragment;
import com.example.datsanbongda.database.DbHelper;

public class DangKiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        Button btnDangki = findViewById(R.id.btnDangki);

        EditText edtHoten = findViewById(R.id.edtHoten);
        EditText edtNhapLaiMk = findViewById(R.id.edtNhapLaiMatkhau);
        EditText edtSdt = findViewById(R.id.edtSdt);
        EditText edtMatkhau = findViewById(R.id.edtMatkhau);
        ImageView imgBack = findViewById(R.id.imgBackDangki);

        Bundle bundle = new Bundle();

        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoten.getText().toString();
                String sdt = edtSdt.getText().toString();
                String matkhau = edtMatkhau.getText().toString();
                String nhaplaimk =edtNhapLaiMk.getText().toString();

                DbHelper dbHelper = new DbHelper(DangKiActivity.this);
                if(hoten.isEmpty() || matkhau.isEmpty() || sdt.isEmpty()){
                    Toast.makeText(DangKiActivity.this, "Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();

                }else {
                    boolean check = dbHelper.KiemTraDangNhap(sdt);
                    if(check){
                        edtSdt.setError("Số điện thoại đã được đăng kí!");
                    }else if(sdt.length()==10 ){

                        if(nhaplaimk.compareTo(matkhau) == 0 ){

                                dbHelper.DangKi(hoten,sdt,matkhau);
                                Toast.makeText(DangKiActivity.this, "Đã tạo thành công tài khoản", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DangKiActivity.this, DangNhapActivity.class));



                        }else {
                            Toast.makeText(DangKiActivity.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        edtSdt.setError("Số điện thoại không phù hợp");
                    }
                }
            }
        });

    }
}