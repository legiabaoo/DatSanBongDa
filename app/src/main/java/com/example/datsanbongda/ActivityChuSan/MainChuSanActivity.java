package com.example.datsanbongda.ActivityChuSan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.example.datsanbongda.FragmantKhachHang.CaNhanFragment;
import com.example.datsanbongda.FragmantKhachHang.HomeFragment;
import com.example.datsanbongda.FragmentChuSan.CaNhanChuSanFragment;
import com.example.datsanbongda.FragmentChuSan.HomeChuSanFragment;
import com.example.datsanbongda.FragmentChuSan.LichSuChuSanFragment;
import com.example.datsanbongda.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainChuSanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chu_san);
        BottomAppBar btnAppbar = findViewById(R.id.bottonAppbar);
        BottomNavigationView btnNavigation = findViewById(R.id.bottonNavigation);

        // Hiển thị HomeFragment khi MainActivity được tạo
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayout, new HomeChuSanFragment())
                .commit();

        btnNavigation.setOnItemSelectedListener(item -> {
            Fragment fragmentButton = null;
            if (item.getItemId() == R.id.Home) {
                fragmentButton = new HomeChuSanFragment();
            } else if (item.getItemId() == R.id.canhann) {
                fragmentButton = new CaNhanChuSanFragment();
            } else if (item.getItemId() == R.id.lichsu) {
                fragmentButton = new LichSuChuSanFragment();
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.FrameLayout, fragmentButton)
                    .commit();

            Log.d("DEBUG", "Fragment selected: " + fragmentButton.getClass().getSimpleName());
            return true;
        });
        btnNavigation.getMenu().findItem(R.id.Home).setChecked(true);
    }
}