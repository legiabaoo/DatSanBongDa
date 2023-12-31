package com.example.datsanbongda.ActivityKhachHang;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.ConfigNotification;
import com.example.datsanbongda.DAO.DatSanDAO;
import com.example.datsanbongda.DAO.LichSuDatSanDAO;

import com.example.datsanbongda.DAO.LichSuDuyetSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.adapter.DatSanAdapter;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DoanhThu;
import com.example.datsanbongda.model.LichSuDatSan;
import com.example.datsanbongda.model.LichSuDuyetSan;
import com.example.datsanbongda.model.MaThanhToan;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatSanActivity extends AppCompatActivity {
    private TextInputEditText tIETGioDB;
    private TextInputEditText tIETNgay;
    private TextInputEditText tIETGioKT, tIETSan;
    private String day_week;
    private String thongbao="";
    private TextInputEditText tIETThongBao;
    private Calendar calendarTime;
    private Calendar currentDate;
    private LichSuDatSanDAO lichSuDatSanDAO;
    private LichSuDuyetSanDAO lichSuDuyetSanDAO;
    private DbHelper dbHelper;
    private int maSan, maLoaiSan;
    private RecyclerView rvDatSan;
    private DatSanDAO datSanDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_san);
        Toolbar tbDatSan = findViewById(R.id.tbDatSan);
        TextInputEditText tIETLoaiSan = findViewById(R.id.tIETLoaiSan);
        tIETSan = findViewById(R.id.tIETSan);
        tIETThongBao = findViewById(R.id.tIETThongBao);
        tIETNgay = findViewById(R.id.tIETNgay);
        tIETGioDB = findViewById(R.id.tIETGioBD);
        tIETGioKT = findViewById(R.id.tIETGioKT);
        Button btnDatSan = findViewById(R.id.btnDatSan1);
        ImageView iv_LoaiSan = findViewById(R.id.iv_LoaiSan);
        ImageView iv_San = findViewById(R.id.iv_San);
        ImageView iv_Ngay = findViewById(R.id.iv_Ngay);
        ImageView iv_GioDB = findViewById(R.id.iv_GioBD);
        ImageView iv_GioKT = findViewById(R.id.iv_GioKT);
        rvDatSan = findViewById(R.id.rvDatSan);
        //loadData
        loadData();

        lichSuDatSanDAO = new LichSuDatSanDAO(DatSanActivity.this);
        lichSuDuyetSanDAO = new LichSuDuyetSanDAO(DatSanActivity.this);
        dbHelper = new DbHelper(DatSanActivity.this);
        //an thanh status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //toolbar
        setSupportActionBar(tbDatSan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setTitle("Đặt sân");

        //getIntent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tIETLoaiSan.setText(bundle.getString("loaisan"));
        tIETSan.setText(bundle.getString("tensan"));

        btnDatSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongbao="";
                String[] gioBD = tIETGioDB.getText().toString().split(":");
                String[] gioKT = tIETGioKT.getText().toString().split(":");

                Calendar calendar = Calendar.getInstance();
                int gioHT = calendar.get(Calendar.HOUR_OF_DAY);
                int phutHT = calendar.get(Calendar.MINUTE);
                int year1=0, month1=0, day1, day2=0;
                if(!tIETNgay.getText().toString().equals("Chọn ngày")){
                    year1 = currentDate.get(Calendar.YEAR);
                    month1 = currentDate.get(Calendar.MONTH);
                    day1 = currentDate.get(Calendar.DAY_OF_MONTH)-1;
                    day2 = day1+1;
                    currentDate.set(year1, month1, day1);
                }

                if(tIETLoaiSan.getText().toString().equals("Chọn loai sân") || tIETSan.getText().toString().equals("Chọn sân") ||
                tIETNgay.getText().toString().equals("Chọn ngày") || tIETGioDB.getText().toString().equals("Chọn giờ bắt đầu") ||
                        tIETGioKT.getText().toString().equals("Chọn giờ kết thúc")) {
                    thongbao = "Vui lòng chọn đầy đủ thông tin";
                    tIETThongBao.setText(thongbao);
                } else if (calendarTime.before(currentDate)) {
                    int month = currentDate.get(Calendar.MONTH)+1;
                    int day = currentDate.get(Calendar.DAY_OF_MONTH)+1;
                    thongbao+="Vui lòng chọn ngày từ "+day+" tháng "+month+" năm "+currentDate.get(Calendar.YEAR);
                    tIETThongBao.setText(thongbao);
                }else if(day2==calendarTime.get(Calendar.DAY_OF_MONTH)&&
                        month1==calendarTime.get(Calendar.MONTH)&&year1==calendarTime.get(Calendar.YEAR)){
                    if (Integer.parseInt(gioBD[0])-gioHT<0 ) {
                        thongbao+="Vui lòng chọn sau "+gioHT+" giờ "+phutHT+" phút";
                        tIETThongBao.setText(thongbao);
                    }else if (Integer.parseInt(gioBD[0])-gioHT==0 ) {
                        if(Integer.parseInt(gioBD[1])-phutHT<0){
                            thongbao+="Vui lòng chọn sau "+gioHT+" giờ "+phutHT+" phút";
                            tIETThongBao.setText(thongbao);}
                    }
                } else if (Integer.parseInt(gioKT[0])-Integer.parseInt(gioBD[0])==1 && Integer.parseInt(gioKT[1])-Integer.parseInt(gioBD[1])<0 ) {
                    thongbao+="Vui lòng chọn thời gian đá hơn 1 tiếng";
                    tIETThongBao.setText(thongbao);
                } else if (Integer.parseInt(gioKT[0])-Integer.parseInt(gioBD[0])<1) {
                    thongbao+="Vui lòng chọn thời gian đá hơn 1 tiếng";
                    tIETThongBao.setText(thongbao);
                }
                else if (datSanDAO.kiemTraDatSan(tIETNgay.getText().toString(), tIETSan.getText().toString(),
                        tIETGioDB.getText().toString(), tIETGioKT.getText().toString())==false) {
                    thongbao+="Lịch này đã được đặt trước";
                    tIETThongBao.setText(thongbao);
                }
                else{
                    String thoiGianBatDau = tIETGioDB.getText().toString();
                    String thoiGianKetThuc = tIETGioKT.getText().toString();
                    String ngay = tIETNgay.getText().toString();
                    int trangThai = 0;
                    String tenSan = tIETSan.getText().toString();

                    calendarTime = Calendar.getInstance();
                    int year = calendarTime.get(Calendar.YEAR);
                    int month = calendarTime.get(Calendar.MONTH);
                    int day = calendarTime.get(Calendar.DAY_OF_MONTH);
                    calendarTime.set(year, month, day);
                    Date selectedDate = calendarTime.getTime();
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String ngayDat = sdf1.format(selectedDate);

                    SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT maSan, maLoaiSan FROM SAN WHERE tenSan = ?", new String[]{tenSan});
                    if (cursor.moveToFirst()) {
                        maSan = cursor.getInt(0);
                        maLoaiSan = cursor.getInt(1);
                    }
                    int maChuSan = 1;
                    int maKhachHang = 1;
                    SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                    String soDienThoai = sharedPreferences.getString("soDienThoai", "");
                    ArrayList<LichSuDuyetSan> list = new ArrayList<>();
                    list = lichSuDuyetSanDAO.getDSDuyetSan();
                    int maVe = list.size()+1;
                    datSanDAO.taoMaThanhToan(soDienThoai, maVe);
                    String maThanhToan = setNoiDung();
                    LichSuDatSan lichSuDatSan = new LichSuDatSan(thoiGianBatDau, thoiGianKetThuc, ngay, ngayDat, trangThai, maSan, maChuSan, maKhachHang);
                    boolean checkLS = lichSuDatSanDAO.themLichSu(lichSuDatSan);
                    LichSuDuyetSan lichSuDuyetSan = new LichSuDuyetSan(thoiGianBatDau, thoiGianKetThuc, ngay, ngayDat, trangThai, maSan, maChuSan, maKhachHang, maThanhToan);
                    boolean checkDS = lichSuDuyetSanDAO.themDuyetSan(lichSuDuyetSan);
//                    if(checkLS && checkDS){
//                        Toast.makeText(DatSanActivity.this, "Đặt sân thành công", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(DatSanActivity.this, "Đặt sân thất bại", Toast.LENGTH_SHORT).show();
//                    }

                    startActivity(new Intent(DatSanActivity.this, ThanhToanActivity.class));
//                    Intent intent = new Intent(DatSanActivity.this, DatChoActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("San", tIETSan.getText().toString());
//                    bundle.putString("GioBD", tIETGioDB.getText().toString());
//                    bundle.putString("GioKT", tIETGioKT.getText().toString());
//                    bundle.putString("Thu", day_week);
//                    bundle.putString("NgayThangNam", tIETNgay.getText().toString());
//                    intent.putExtras(bundle);
//                    startActivity(intent);
                }
            }
        });

//        tIETLoaiSan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String[] loaiSan = {"Sân 5", "Sân 7"};
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(DatSanActivity.this);
//                builder.setTitle("Chọn loại sân bạn muốn đặt");
//                builder.setItems(loaiSan, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        tIETLoaiSan.setText(loaiSan[which]);
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
//
//        tIETSan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tIETLoaiSan.getText().toString().equals("Sân 5")) {
//                    String[] san = {"Sân số 1", "Sân số 2", "Sân số 3"};
//                    AlertDialog.Builder builder = new AlertDialog.Builder(DatSanActivity.this);
//                    builder.setTitle("Chọn sân bạn muốn đặt");
//                    builder.setItems(san, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            tIETSan.setText(san[which]);
//                        }
//                    });
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//                }
//                if (tIETLoaiSan.getText().toString().equals("Sân 7")) {
//                    String[] san = {"Sân số 4", "Sân số 5"};
//                    AlertDialog.Builder builder = new AlertDialog.Builder(DatSanActivity.this);
//                    builder.setTitle("Chọn sân bạn muốn đặt");
//                    builder.setItems(san, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            tIETSan.setText(san[which]);
//                        }
//                    });
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//                }
//            }
//        });
        tIETGioDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SwipeTimePickerDialog timePickerDialog = new SwipeTimePickerDialog(DatSanActivity.this);
//                timePickerDialog.show();
                showTimePickerDialogStart();
            }
        });

        tIETGioKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogEnd();
            }
        });

        tIETNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void loadData() {
        datSanDAO = new DatSanDAO(this);
        ArrayList<DoanhThu> list = new ArrayList<>();
        String ngay = tIETNgay.getText().toString();
        String tenSan = tIETSan.getText().toString();
        list = datSanDAO.getDSGio(ngay, tenSan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvDatSan.setLayoutManager(linearLayoutManager);
        DatSanAdapter datSanAdapter = new DatSanAdapter(list, datSanDAO, this);
        rvDatSan.setAdapter(datSanAdapter);
    }

    private void showTimePickerDialogStart() {
        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo đối tượng TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Xử lý khi người dùng chọn giờ và phút
                        // Ở đây, bạn có thể làm gì đó với giờ và phút được chọn
                        if(minute != 0 && minute !=30 ){
                            if(hourOfDay<10){
                                minute = (minute<30)?0:30;
                                if(minute==0){
                                    String selectedTime = "0"+hourOfDay + ":0" + minute;
                                    tIETGioDB.setText(selectedTime);
                                }else{
                                    String selectedTime = "0"+hourOfDay + ":" + minute;
                                    tIETGioDB.setText(selectedTime);
                                }
                            }else{
                                minute = (minute<30)?0:30;
                                if(minute==0){
                                    String selectedTime = hourOfDay + ":0" + minute;
                                    tIETGioDB.setText(selectedTime);
                                }else{
                                    String selectedTime = hourOfDay + ":" + minute;
                                    tIETGioDB.setText(selectedTime);
                                }
                            }
                        }else if (minute==0){
                            if(hourOfDay<10){
                                String selectedTime = "0"+hourOfDay + ":0" + minute;
                                tIETGioDB.setText(selectedTime);
                            }else{
                                String selectedTime = hourOfDay + ":0" + minute;
                                tIETGioDB.setText(selectedTime);
                            }
                        } else if (minute==30) {
                            if(hourOfDay<10){
                                String selectedTime = "0"+hourOfDay + ":" + minute;
                                tIETGioDB.setText(selectedTime);
                            }else{
                                String selectedTime = hourOfDay + ":" + minute;
                                tIETGioDB.setText(selectedTime);
                            }
                        }
                    }
                },
                hour,
                minute,
                true // Cho phép 24 giờ
        );

        // Hiển thị dialog chọn giờ
        timePickerDialog.show();
    }
    private void showTimePickerDialogEnd() {
        // Lấy thời gian hiện tại
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Tạo đối tượng TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Xử lý khi người dùng chọn giờ và phút
                        // Ở đây, bạn có thể làm gì đó với giờ và phút được chọn
                        if(minute != 0 && minute !=30 ){
                            if(hourOfDay<10){
                                minute = (minute<30)?0:30;
                                if(minute==0){
                                    String selectedTime = "0"+hourOfDay + ":0" + minute;
                                    tIETGioKT.setText(selectedTime);
                                }else{
                                    String selectedTime = "0"+hourOfDay + ":" + minute;
                                    tIETGioKT.setText(selectedTime);
                                }
                            }else{
                                minute = (minute<30)?0:30;
                                if(minute==0){
                                    String selectedTime = hourOfDay + ":0" + minute;
                                    tIETGioKT.setText(selectedTime);
                                }else{
                                    String selectedTime = hourOfDay + ":" + minute;
                                    tIETGioKT.setText(selectedTime);
                                }
                            }
                        }else if (minute==0){
                            if(hourOfDay<10){
                                String selectedTime = "0"+hourOfDay + ":0" + minute;
                                tIETGioKT.setText(selectedTime);
                            }else{
                                String selectedTime = hourOfDay + ":0" + minute;
                                tIETGioKT.setText(selectedTime);
                            }
                        } else if (minute==30) {
                            if(hourOfDay<10){
                                String selectedTime = "0"+hourOfDay + ":" + minute;
                                tIETGioKT.setText(selectedTime);
                            }else{
                                String selectedTime = hourOfDay + ":" + minute;
                                tIETGioKT.setText(selectedTime);
                            }
                        }
                    }
                },
                hour,
                minute,
                true // Cho phép 24 giờ
        );

        // Hiển thị dialog chọn giờ
        timePickerDialog.show();
    }
    private void showDatePickerDialog() {
        // Lấy thời gian hiện tại
        calendarTime = Calendar.getInstance();
        int year = calendarTime.get(Calendar.YEAR);
        int month = calendarTime.get(Calendar.MONTH);
        int day = calendarTime.get(Calendar.DAY_OF_MONTH);

        // Tạo dialog chọn ngày

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                //Xử lí sự kiện khi người dùng chọn ngày
                calendarTime.set(year, month, dayOfMonth);
                Date selectedDate = calendarTime.getTime();

                //Định dạng ngày để hiển thị thứ
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
                day_week = sdf.format(selectedDate);
                //bat loi chon ngay
                currentDate = Calendar.getInstance();
                //Set len TextView
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formatDate = sdf1.format(selectedDate);
                tIETNgay.setText(formatDate);
                loadData();
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
    private String setNoiDung(){
        ArrayList<MaThanhToan> list = new ArrayList<>();
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
}