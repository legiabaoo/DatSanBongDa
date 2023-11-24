package com.example.datsanbongda.ActivityKhachHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.datsanbongda.FragmantKhachHang.CaNhanFragment;
import com.example.datsanbongda.FragmantKhachHang.HomeFragment;

import com.example.datsanbongda.LichSuFragment;
import com.example.datsanbongda.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        BottomAppBar btnAppbar = findViewById(R.id.bottonAppbar);
        BottomNavigationView btnNavigation = findViewById(R.id.bottonNavigation);




        // Hiển thị HomeFragment khi MainActivity được tạo
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FrameLayout, new HomeFragment())
                .commit();

        btnNavigation.setOnItemSelectedListener(item -> {
            Fragment fragmentButton = null;
            if (item.getItemId() == R.id.Home) {
                fragmentButton = new HomeFragment();
            } else if (item.getItemId() == R.id.canhann) {
                fragmentButton = new CaNhanFragment();
            } else if (item.getItemId() == R.id.lichsu) {
                fragmentButton = new LichSuFragment();
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