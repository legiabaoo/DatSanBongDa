package com.example.datsanbongda.ActivityKhachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class Doi_Mk_KH_Activity extends AppCompatActivity {
    DbHelper db;
    TextInputEditText SDTforgot;
    Button btnTiepTuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mk_kh);
        ImageView iv = findViewById(R.id.imgBackQuenMK);
        SDTforgot = findViewById(R.id.SDTforgot);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        db = new DbHelper(this);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soDienThoai = SDTforgot.getText().toString();
                boolean check =db.KiemTraDangNhap(soDienThoai);
                if(check){
                Intent intent =new Intent(Doi_Mk_KH_Activity.this,Xac_Nhan_DMK_Activity.class);
                intent.putExtra("user",soDienThoai);
                startActivity(intent);
                }else {
                    Toast.makeText(Doi_Mk_KH_Activity.this, "Số điện thoại không tồn tại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    }
}