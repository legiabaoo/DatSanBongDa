package com.example.datsanbongda.ActivityKhachHang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.ActivityChuSan.ThongTinSanChuSanActivity;
import com.example.datsanbongda.DAO.DanhGiaDAO;
import com.example.datsanbongda.DAO.ThongTinSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.adapter.DanhGiaAdapter;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DanhGia;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChiTietSanActivity extends AppCompatActivity {
    private ArrayList<DanhGia> list;
    private DanhGiaDAO danhGiaDAO;
    private RecyclerView rclViewChiTietSan;
    private DbHelper dbHelper;
    private String loaiSann;
    private int tienSanSang;
    private int tienSanToi;
    TextView txtgiaSang;    TextView txtgiaToi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san);
        rclViewChiTietSan = findViewById(R.id.rclViewChiTietSan);
        Button btnDatSan = findViewById(R.id.btnDatSan);
        Toolbar tbChiTietSan = findViewById(R.id.tbChiTietSan);
        TextView txtTenSan = findViewById(R.id.txtTenSanCTS);
        TextView txtLoaiSan = findViewById(R.id.txtLoaiSanCTS);
        TextView txtTrangThai = findViewById(R.id.txtTrangThaiCTS);
        txtgiaSang = findViewById(R.id.txtGiaChiTietSang);
        txtgiaToi = findViewById(R.id.txtGiaChiTietToi);

        setSupportActionBar(tbChiTietSan);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        //data
        danhGiaDAO = new DanhGiaDAO(this);

        //adapter
        loadData();




        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String san7 =bundle.getString("masan");
        setGiaSan7(san7);


        txtLoaiSan.setText(bundle.getString("loaisan"));
        txtTenSan.setText(bundle.getString("tensan"));
        if(bundle.getString("trangthai").equals("Đang hoạt động")){
            txtTrangThai.setText(bundle.getString("trangthai"));
        } else if (bundle.getString("trangthai").equals("Ngừng hoạt động")) {
            txtTrangThai.setText(bundle.getString("trangthai"));
            txtTrangThai.setTextColor(ContextCompat.getColor(ChiTietSanActivity.this, R.color.colorThatBai));
        }

        btnDatSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bundle.getString("trangthai").equals("Đang hoạt động")){
                    Intent intent1 = new Intent(ChiTietSanActivity.this, DatSanActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("loaisan", bundle.getString("loaisan"));
                    bundle1.putString("tensan", bundle.getString("tensan"));
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                } else if (bundle.getString("trangthai").equals("Ngừng hoạt động")) {
                    Toast.makeText(ChiTietSanActivity.this, "Sân này hiện tại đang ngừng hoạt động", Toast.LENGTH_SHORT).show();
                }

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
    public void setGiaSan7(String masan){
        dbHelper = new DbHelper(this);
        list = new ArrayList<>();
        // sân 7
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISAN WHERE maLoaiSan=?", new String[]{masan});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            loaiSann = cursor.getString(1);
            tienSanSang = cursor.getInt(2);
            tienSanToi = cursor.getInt(3);
        }
        int tienSanSang1 = tienSanSang;
        String tienSanSang2 = dinhdangtien(tienSanSang1);
        int tienSanToi1 = tienSanToi;
        String tienSanToi2 = dinhdangtien(tienSanToi1);
        txtgiaSang.setText(String.valueOf(tienSanSang2));
        txtgiaToi.setText(String.valueOf(tienSanToi2));

    }
    private String dinhdangtien(int amount) {
        // Tạo một đối tượng NumberFormat với Locale.getDefault() để định dạng theo ngôn ngữ và quốc gia của thiết bị
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        // Chuyển đổi int thành định dạng tiền tệ và trả về kết quả
        return currencyFormatter.format(amount);
    }
}