package com.example.datsanbongda.ActivityKhachHang;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LichSuDatSan;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatSanActivity extends AppCompatActivity {
    private TextInputEditText tIETGioDB;
    private TextInputEditText tIETNgay;
    private TextInputEditText tIETGioKT;
    private String day_week;
    private String thongbao="";
    private TextInputEditText tIETThongBao;
    private Calendar calendarTime;
    private Calendar currentDate;
    private LichSuDatSanDAO lichSuDatSanDAO;
    private DbHelper dbHelper;
    private int maSan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_san);
        Toolbar tbDatSan = findViewById(R.id.tbDatSan);
        TextInputEditText tIETLoaiSan = findViewById(R.id.tIETLoaiSan);
        TextInputEditText tIETSan = findViewById(R.id.tIETSan);
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
        lichSuDatSanDAO = new LichSuDatSanDAO(DatSanActivity.this);
        dbHelper = new DbHelper(DatSanActivity.this);
        //an thanh status
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //toolbar
        setSupportActionBar(tbDatSan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setTitle("");

        btnDatSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongbao="";
                String[] gioBD = tIETGioDB.getText().toString().split(":");
                String[] gioKT = tIETGioKT.getText().toString().split(":");
                Calendar calendar = Calendar.getInstance();
                int gioHT = calendar.get(Calendar.HOUR_OF_DAY);
                int phutHT = calendar.get(Calendar.MINUTE);
                if(tIETLoaiSan.getText().toString().equals("Chọn loai sân") || tIETSan.getText().toString().equals("Chọn sân") ||
                tIETNgay.getText().toString().equals("Chọn ngày") || tIETGioDB.getText().toString().equals("Chọn giờ bắt đầu") ||
                        tIETGioKT.getText().toString().equals("Chọn giờ kết thúc")) {
                    thongbao = "Vui lòng chọn đầy đủ thông tin";
                    tIETThongBao.setText(thongbao);
                } else if (calendarTime.before(currentDate)) {
                    thongbao+="Vui lòng chọn ngày sau "+calendarTime.get(Calendar.DAY_OF_MONTH)+" tháng "+calendarTime.get(Calendar.MONTH)+" năm "+calendarTime.get(Calendar.YEAR);
                    tIETThongBao.setText(thongbao);
                } else if (Integer.parseInt(gioBD[0])-gioHT<0 ) {
                    thongbao+="Vui lòng chọn sau "+gioHT+" giờ "+phutHT+" phút";
                    tIETThongBao.setText(thongbao);
                }else if (Integer.parseInt(gioBD[0])-gioHT==0 ) {
                    if(Integer.parseInt(gioBD[1])-phutHT<0){
                    thongbao+="Vui lòng chọn sau "+gioHT+" giờ "+phutHT+" phút";
                    tIETThongBao.setText(thongbao);}
                } else if (Integer.parseInt(gioKT[0])-Integer.parseInt(gioBD[0])==1 && Integer.parseInt(gioKT[1])-Integer.parseInt(gioBD[1])<0 ) {
                    thongbao+="Vui lòng chọn thời gian đá hơn 1 tiếng";
                    tIETThongBao.setText(thongbao);
                } else if (Integer.parseInt(gioKT[0])-Integer.parseInt(gioBD[0])<1) {
                    thongbao+="Vui lòng chọn thời gian đá hơn 1 tiếng";
                    tIETThongBao.setText(thongbao);
                } else{
                    String thoiGianBatDau = tIETGioDB.getText().toString();
                    String thoiGianKetThuc = tIETGioKT.getText().toString();
                    String ngay = tIETNgay.getText().toString();
                    int trangThai = 0;
                    String tenSan = tIETSan.getText().toString();
                    SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                    Cursor cursor = sqLiteDatabase.rawQuery("SELECT maSan FROM SAN WHERE tenSan = ?", new String[]{tenSan});
                    if (cursor.moveToFirst()) {
                        maSan = cursor.getInt(0);
                        // Ở đây bạn có thể sử dụng giá trị ID (maVe) theo nhu cầu của mình
                    }
                    int maChuSan = 1;
                    int maKhachHang = 1;
                    LichSuDatSan lichSuDatSan = new LichSuDatSan(thoiGianBatDau, thoiGianKetThuc, ngay, trangThai, maSan, maChuSan, maKhachHang);
                    boolean check = lichSuDatSanDAO.themLichSu(lichSuDatSan);
                    if(check){
                        Toast.makeText(DatSanActivity.this, "Them Thanh Cong", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DatSanActivity.this, "Them That Bai", Toast.LENGTH_SHORT).show();
                    }
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

        tIETLoaiSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] loaiSan = {"Sân 5", "Sân 7"};

                AlertDialog.Builder builder = new AlertDialog.Builder(DatSanActivity.this);
                builder.setTitle("Chọn loại sân bạn muốn đặt");
                builder.setItems(loaiSan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tIETLoaiSan.setText(loaiSan[which]);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        tIETSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tIETLoaiSan.getText().toString().equals("Sân 5")) {
                    String[] san = {"Sân số 1", "Sân số 2", "Sân số 3"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(DatSanActivity.this);
                    builder.setTitle("Chọn sân bạn muốn đặt");
                    builder.setItems(san, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tIETSan.setText(san[which]);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                if (tIETLoaiSan.getText().toString().equals("Sân 7")) {
                    String[] san = {"Sân số 4", "Sân số 5"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(DatSanActivity.this);
                    builder.setTitle("Chọn sân bạn muốn đặt");
                    builder.setItems(san, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            tIETSan.setText(san[which]);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
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
                        if(minute != 0 && minute !=30){
                            minute = (minute<30)?0:30;
                        }
                        if(minute==0){
                            String selectedTime = hourOfDay + ":0" + minute;
                            tIETGioDB.setText(selectedTime);
                        }else{
                            String selectedTime = hourOfDay + ":" + minute;
                            tIETGioDB.setText(selectedTime);
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
                        if(minute != 0 && minute !=30){
                            minute = (minute<30)?0:30;
                        }
                        if(minute==0){
                            String selectedTime = hourOfDay + ":0" + minute;
                            tIETGioKT.setText(selectedTime);
                        }else{
                            String selectedTime = hourOfDay + ":" + minute;
                            tIETGioKT.setText(selectedTime);
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
}