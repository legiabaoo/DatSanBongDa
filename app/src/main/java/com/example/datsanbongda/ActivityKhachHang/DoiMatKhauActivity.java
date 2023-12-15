package com.example.datsanbongda.ActivityKhachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datsanbongda.DangNhapActivity;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class DoiMatKhauActivity extends AppCompatActivity {

    Button btn_XN;
    TextInputEditText Mk_moi;
    TextInputEditText Mk_NhapLai;
    TextInputEditText Mk_cu;
    TextView txt_sdt;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        btn_XN = findViewById(R.id.btnXacNhanDMK);
        Mk_moi = findViewById(R.id.edt_MkMoi);
        Mk_NhapLai = findViewById(R.id.edt_MkNhapLai);
        Mk_cu =findViewById(R.id.Mk_cu);
        txt_sdt = findViewById(R.id.txt_doimk);
        ImageView img = findViewById(R.id.imgBackDoiMK);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db = new DbHelper(this);



        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String sdt = sharedPreferences.getString("soDienThoai", "");
        txt_sdt.setText("0"+sdt);


        btn_XN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtreset = txt_sdt.getText().toString();
                String newpass = Mk_moi.getText().toString();
                String oldpass = Mk_cu.getText().toString();
                String newconfi = Mk_NhapLai.getText().toString();

                if (oldpass.isEmpty() || newpass.isEmpty() || newconfi.isEmpty()) {
                    Toast.makeText(DoiMatKhauActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    // Kiểm tra mật khẩu cũ bằng hàm KiemTraPass
                    if (db.KiemTraPass(txtreset,oldpass)) {
                        // Mật khẩu cũ đúng
                        if (newconfi.compareTo(newpass) == 0) {
                            boolean check = db.updatePassKH(txtreset, newpass);
                            if (check) {
                                startActivity(new Intent(DoiMatKhauActivity.this, MainActivity.class));
                                Toast.makeText(DoiMatKhauActivity.this, "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu chưa được cập nhật!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(DoiMatKhauActivity.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

    }

    }
