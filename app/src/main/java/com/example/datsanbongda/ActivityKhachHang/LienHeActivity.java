package com.example.datsanbongda.ActivityKhachHang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.datsanbongda.R;

public class LienHeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien_he);
        Button btnXacNhan = findViewById(R.id.btnXacNhanLienHe);
        ImageView imgback = findViewById(R.id.ImgBack);

        LinearLayout LN_fb = findViewById(R.id.LN_fb);
        LinearLayout LN_dt = findViewById(R.id.LN_dt);


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LN_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebookPage();
            }
        });
        LN_dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }


    private void openFacebookPage() {
        try {
            // Kiểm tra xem ứng dụng Facebook đã được cài đặt trên thiết bị hay chưa
            getPackageManager().getPackageInfo("com.facebook.katana", 0);

            // Nếu có, mở ứng dụng Facebook
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/100016696309020"));
            startActivity(intent);
        } catch (Exception e) {
            // Nếu không có ứng dụng Facebook, mở trang web
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100016696309020"));
            startActivity(intent);
        }
    }
    private void makePhoneCall() {
        // Số điện thoại cần gọi
        String phoneNumber = "tel:" + "0386881033";

        // Tạo Intent để mở màn hình gọi điện thoại
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(phoneNumber));

        // Kiểm tra xem thiết bị có ứng dụng gọi điện thoại hay không
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            // Mở màn hình gọi điện thoại
            startActivity(dialIntent);
        } else {
            // Xử lý khi không tìm thấy ứng dụng gọi điện thoại
            // Có thể hiển thị thông báo hoặc xử lý khác tùy thuộc vào yêu cầu của bạn
        }
    }
}