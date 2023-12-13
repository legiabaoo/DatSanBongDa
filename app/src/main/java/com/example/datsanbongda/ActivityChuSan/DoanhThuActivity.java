package com.example.datsanbongda.ActivityChuSan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datsanbongda.DAO.DoanhThuDAO;
import com.example.datsanbongda.DAO.LichSuDuyetSanDAO;
import com.example.datsanbongda.adapter.DoanhThuAdapter;
import com.example.datsanbongda.adapter.LichSuDuyetSanAdapter;
import com.example.datsanbongda.model.DoanhThu;
import com.example.datsanbongda.model.LichSuDuyetSan;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.datsanbongda.R;

public class DoanhThuActivity extends AppCompatActivity {
    private String day_week;
    private EditText tIETNgayBD, tIETNgayKT;
    private RecyclerView rvDoanhThu;
    private DoanhThuDAO doanhThuDAO;
    private TextView txtTongDoanhThu;
    private Calendar currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        ImageView imgBack = findViewById(R.id.imgBackDoanhThu);
        tIETNgayBD = findViewById(R.id.tIETNgayDoanhThuBD);
        tIETNgayKT = findViewById(R.id.tIETNgayDoanhthuKT);
        txtTongDoanhThu = findViewById(R.id.txtTongDoanhThu);

        rvDoanhThu = findViewById(R.id.rvDoanhThu);

        loadData();

//        txtTongDoanhThu.setText(dinhdangtien(doanhThuDAO.tongDoanhThu(tIETNgayBD.getText().toString(), tIETNgayKT.getText().toString())));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tIETNgayBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogBD();
            }
        });
        tIETNgayKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogKT();
            }
        });
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    }

    private void loadData() {
        ArrayList<DoanhThu> list = new ArrayList<>();
        doanhThuDAO = new DoanhThuDAO(DoanhThuActivity.this);
        String ngayBD = tIETNgayBD.getText().toString();
        String ngayKT = tIETNgayKT.getText().toString();
        list = doanhThuDAO.getDSDoanhThu(ngayBD, ngayKT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvDoanhThu.setLayoutManager(linearLayoutManager);
        DoanhThuAdapter doanhThuAdapter = new DoanhThuAdapter(this, list, doanhThuDAO);
        rvDoanhThu.setAdapter(doanhThuAdapter);
        txtTongDoanhThu.setText(dinhdangtien(doanhThuDAO.tongDoanhThu(ngayBD, ngayKT)));
    }


    private void showDatePickerDialogBD() {
        // Lấy thời gian hiện tại
        Calendar calendarTime = Calendar.getInstance();
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
                tIETNgayBD.setText(formatDate);
                loadData();
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showDatePickerDialogKT() {
        // Lấy thời gian hiện tại
        Calendar calendarTime = Calendar.getInstance();
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
                tIETNgayKT.setText(formatDate);
                loadData();
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private String dinhdangtien(int amount) {
        // Tạo một đối tượng NumberFormat với Locale.getDefault() để định dạng theo ngôn ngữ và quốc gia của thiết bị
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        // Chuyển đổi int thành định dạng tiền tệ và trả về kết quả
        return currencyFormatter.format(amount);
    }

}