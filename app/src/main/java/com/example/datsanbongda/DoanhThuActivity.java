package com.example.datsanbongda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DoanhThuActivity extends AppCompatActivity {
    private String day_week;
    private EditText tIETNgay;

    private Calendar currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);

        tIETNgay = findViewById(R.id.tIETNgayDoanhthu);



        tIETNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialogBD();
            }
        });
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
                tIETNgay.setText(formatDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }


}