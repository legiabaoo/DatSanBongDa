package com.example.datsanbongda.FragmentChuSan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.datsanbongda.ActivityChuSan.DoanhThuActivity;
import com.example.datsanbongda.ActivityChuSan.LienHeChuSanActivity;
import com.example.datsanbongda.DangNhapActivity;
import com.example.datsanbongda.R;


public class CaNhanChuSanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_ca_nhan_chu_san, container, false);
        Button btnDangXuatChuSan = view.findViewById(R.id.btnDangXuatChuSan);
        Button btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        Button btnLienHe = view.findViewById(R.id.btnLienHecaNhan);

        btnLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LienHeChuSanActivity.class));
            }
        });



        btnDangXuatChuSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DangNhapActivity.class));
                getActivity().finish();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DoanhThuActivity.class));
            }
        });

        return view;
    }
}