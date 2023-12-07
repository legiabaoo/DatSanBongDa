package com.example.datsanbongda.ActivityKhachHang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.datsanbongda.R;

import java.text.NumberFormat;
import java.util.Locale;

public class DatChoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_cho);
        TextView txtSan = findViewById(R.id.txtTenSanDatCho);
        TextView txtGio = findViewById(R.id.txtGio);
        TextView txtGia = findViewById(R.id.txtGia);
        TextView txtThu = findViewById(R.id.txtThu);
        TextView txtThangNam = findViewById(R.id.txtThangNam);
        TextView txtNgay = findViewById(R.id.txtNgay);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        //toolbar
        Toolbar tbDatSanChiTiet = findViewById(R.id.tbDatSanChiTiet);
        setSupportActionBar(tbDatSanChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setTitle("");


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String san = bundle.getString("San");

        String[] gioBD = bundle.getString("GioBD").split(":");
        int igioBD = Integer.parseInt(gioBD[0]);
        int iphutBD = Integer.parseInt(gioBD[1]);
        String[] gioKT = bundle.getString("GioKT").split(":");
        int igioKT = Integer.parseInt(gioKT[0]);
        int iphutKT = Integer.parseInt(gioKT[1]);
//
        String thu = bundle.getString("Thu");
        String[] date = bundle.getString("NgayThangNam").split("/");
        int tienSan = bundle.getInt("TienSan");
        String day = String.valueOf(date[0]);
        String thang = String.valueOf(date[1]);
        String nam  = String.valueOf(date[2]);

        txtThangNam.setText("Tháng "+thang+", "+nam);
        txtNgay.setText(day);
        txtThu.setText(thu);
        txtGia.setText(dinhdangtien(tienSan));
        txtGio.setText(bundle.getString("GioBD")+" - "+bundle.getString("GioKT"));
        txtSan.setText(san);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private String dinhdangtien(int amount) {
        // Tạo một đối tượng NumberFormat với Locale.getDefault() để định dạng theo ngôn ngữ và quốc gia của thiết bị
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        // Chuyển đổi int thành định dạng tiền tệ và trả về kết quả
        return currencyFormatter.format(amount);
    }
}