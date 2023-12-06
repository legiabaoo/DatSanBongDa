package com.example.datsanbongda.ActivityKhachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.MaThanhToan;

import java.util.ArrayList;

public class ThanhToanActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private ArrayList<MaThanhToan> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Button btnXacNhanHoanTat = findViewById(R.id.btnXacNhanThanhToan);
        TextView txtNoiDungChuyenTien = findViewById(R.id.txtNoiDungChuyenTien);
        CheckBox ckbDongY = findViewById(R.id.ckbDongY);
        Toolbar toolbarThanhToan = findViewById(R.id.toolbarThanhToan);
        list = new ArrayList<>();
        dbHelper = new DbHelper(this);

        setSupportActionBar(toolbarThanhToan);
        getSupportActionBar().setTitle("Thanh Toán");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        txtNoiDungChuyenTien.setText(setNoiDung());
        txtNoiDungChuyenTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToCopy = txtNoiDungChuyenTien.getText().toString();

                // Gọi hàm sao chép
                copyToClipboard(textToCopy);

                // Hiển thị thông báo cho người dùng
                Toast.makeText(ThanhToanActivity.this, "Đã sao chép", Toast.LENGTH_SHORT).show();
            }
        });
        btnXacNhanHoanTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckbDongY.isChecked()){
                    startActivity(new Intent(ThanhToanActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(ThanhToanActivity.this, "Vui lòng chọn vào ô đồng ý với chính sách của sân", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private String setNoiDung(){
        int count=0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM MATHANHTOAN",null);
        if(cursor.moveToFirst()){
            do {
                list.add(new MaThanhToan(cursor.getInt(0),
                        cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        count=list.size();
        String sdt = list.get(count-1).getNoiDung().substring(6, 9);
        String maTT = String.valueOf(list.get(count-1).getMaThanhToan());
        if(maTT.length()==1){
            maTT="00"+maTT;
        } else if (maTT.length()==2) {
            maTT="0"+maTT;
        }
        return sdt+maTT;
    }
    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", text);
        clipboard.setPrimaryClip(clip);
    }
}