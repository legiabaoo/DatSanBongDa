package com.example.datsanbongda.ActivityKhachHang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.DAO.DanhGiaDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.adapter.DanhGiaAdapter;
import com.example.datsanbongda.model.DanhGia;
import java.util.ArrayList;

public class ChiTietSanActivity extends AppCompatActivity {
    private ArrayList<DanhGia> list;
    private DanhGiaDAO danhGiaDAO;
    private RecyclerView rclViewChiTietSan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san);
        rclViewChiTietSan = findViewById(R.id.rclViewChiTietSan);
        Button btnDatSan = findViewById(R.id.btnDatSan);
        Toolbar tbChiTietSan = findViewById(R.id.tbChiTietSan);
        setSupportActionBar(tbChiTietSan);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        //data
        danhGiaDAO = new DanhGiaDAO(this);

        //adapter
        loadData();

        btnDatSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChiTietSanActivity.this, DatSanActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_chitietsan, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        if(item.getItemId()==R.id.mDanhGia){
            Toast.makeText(this, "Danh Gia", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        list = danhGiaDAO.getDSDanhGia();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rclViewChiTietSan.setLayoutManager(linearLayoutManager);
        DanhGiaAdapter danhGiaAdapter = new DanhGiaAdapter(this, list, danhGiaDAO);
        rclViewChiTietSan.setAdapter(danhGiaAdapter);
    }
}