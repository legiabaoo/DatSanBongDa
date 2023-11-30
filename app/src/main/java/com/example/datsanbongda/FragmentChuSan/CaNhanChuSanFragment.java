package com.example.datsanbongda.FragmentChuSan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.datsanbongda.DangNhapActivity;
import com.example.datsanbongda.R;


public class CaNhanChuSanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_ca_nhan_chu_san, container, false);
        Button btnDangXuatChuSan = view.findViewById(R.id.btnDangXuatChuSan);
        btnDangXuatChuSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DangNhapActivity.class));
                getActivity().finish();
            }
        });
        return view;
    }
}