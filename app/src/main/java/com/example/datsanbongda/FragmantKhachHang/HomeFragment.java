package com.example.datsanbongda.FragmantKhachHang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.datsanbongda.ActivityKhachHang.ChiTietSanActivity;
import com.example.datsanbongda.DangNhapActivity;
import com.example.datsanbongda.R;


public class HomeFragment extends Fragment {
 //   private String userFullName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button btnXemChiTiet = view.findViewById(R.id.btnXemChiTiet);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//           String userFullName = bundle.getString("tenKhachHang", "");
//        }

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
        String tenkh = sharedPreferences.getString("tenkh", "");

        // Hiển thị thông tin trên TextView
        TextView textViewUserName = view.findViewById(R.id.txtTenKH);
        textViewUserName.setText(tenkh);

        btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChiTietSanActivity.class));
            }
        });

        return view;
    }
}