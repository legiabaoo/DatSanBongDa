package com.example.datsanbongda.ActivityChuSan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datsanbongda.ActivityKhachHang.DatSanActivity;
import com.example.datsanbongda.ActivityKhachHang.ThongTinSanActivity;
import com.example.datsanbongda.R;

public class ThongTinSanChuSanActivity extends AppCompatActivity {

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
        btnChinhSuaSan7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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