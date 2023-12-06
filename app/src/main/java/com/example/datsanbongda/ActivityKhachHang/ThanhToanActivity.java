package com.example.datsanbongda.ActivityKhachHang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datsanbongda.ConfigNotification;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.MaThanhToan;

import java.util.ArrayList;
import java.util.Date;

public class ThanhToanActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private ArrayList<MaThanhToan> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        Button btnXacNhanHoanTat = findViewById(R.id.btnXacNhanThanhToan);
        TextView txtNoiDungChuyenTien = findViewById(R.id.txtNoiDungChuyenTien);
        TextView txtChinhSach = findViewById(R.id.txtChinhSach);
        CheckBox ckbDongY = findViewById(R.id.ckbDongY);
        Toolbar toolbarThanhToan = findViewById(R.id.toolbarThanhToan);
        list = new ArrayList<>();
        dbHelper = new DbHelper(this);

        setSupportActionBar(toolbarThanhToan);
        getSupportActionBar().setTitle("Thanh Toán");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        txtChinhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThanhToanActivity.this);
                LayoutInflater inflater = (ThanhToanActivity.this).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_chinhsachbaoat,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();

                Button btntrove = view.findViewById(R.id.btntrove);

                btntrove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

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
                    Toast.makeText(ThanhToanActivity.this, "Đặt sân thành công, hãy chờ chủ sân duyệt", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ThanhToanActivity.this, MainActivity.class));
                    sendNotification();
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 7979) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    sendNotification();
                }
            }
        }
    }
    private void sendNotification() {
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.sanbongda);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ConfigNotification.CHANNEL_ID)
                .setSmallIcon(R.drawable.logosan)
                .setContentTitle("Khách đặt sân kìa")
                .setContentText("Vào duyệt ngay thôi!!!")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(logo)
                        .bigLargeIcon(null))
                .setLargeIcon(logo)
                .setColor(Color.BLUE)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

        }    else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.POST_NOTIFICATIONS},7979);
        }
        notificationManagerCompat.notify((int) new Date().getTime(), builder.build());
    }
}