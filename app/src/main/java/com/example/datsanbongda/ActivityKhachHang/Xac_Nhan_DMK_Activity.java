package com.example.datsanbongda.ActivityKhachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
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

public class Xac_Nhan_DMK_Activity extends AppCompatActivity {
    TextInputEditText Mk_moi;
    TextInputEditText Mk_NhapLai;
    Button btnXacNhan;
    TextView txt_sdt;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_dmk);

        txt_sdt = findViewById(R.id.txt_sdt);
        Mk_moi = findViewById(R.id.Mk_Moi);
        Mk_NhapLai = findViewById(R.id.MK_NhapLai);
        btnXacNhan =findViewById(R.id.btnXacNhan);
        ImageView img = findViewById(R.id.imgBackQuenMK);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db = new DbHelper(this);
        Intent intent = getIntent();
        txt_sdt.setText(intent.getStringExtra("user"));

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtreset = txt_sdt.getText().toString();
                String newpass = Mk_moi.getText().toString();
                String newconfi = Mk_NhapLai.getText().toString();

                if (txtreset.isEmpty() || newpass.isEmpty() || newconfi.isEmpty()) {
                    Toast.makeText(Xac_Nhan_DMK_Activity.this, "Vui lòng nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }

                else {
                    if (newconfi.compareTo(newpass) == 0) {
                        boolean check = db.updatePassKH(txtreset, newpass);
                        if (check) {
                            startActivity(new Intent(Xac_Nhan_DMK_Activity.this, DangNhapActivity.class));
                            Toast.makeText(Xac_Nhan_DMK_Activity.this, "Cập nhật mật khẩu thành công !", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Xac_Nhan_DMK_Activity.this, "Mật khẩu chưa được cập nhật!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Xac_Nhan_DMK_Activity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

    }
}