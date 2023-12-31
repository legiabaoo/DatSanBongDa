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

import com.example.datsanbongda.ActivityChuSan.DoanhThuActivity;
import com.example.datsanbongda.ActivityKhachHang.DoiMatKhauActivity;
import com.example.datsanbongda.ActivityKhachHang.LienHeActivity;
import com.example.datsanbongda.ActivityKhachHang.Xac_Nhan_DMK_Activity;
import com.example.datsanbongda.DangNhapActivity;
import com.example.datsanbongda.R;

public class CaNhanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        Button btnLienHe = view.findViewById(R.id.btnLienHeChuSan);
        TextView txtTenKh = view.findViewById(R.id.txtTenKHCaNhan);
        TextView txtSdt = view.findViewById(R.id.txtSdtKH);
        Button btnDangXuatKhachHang = view.findViewById(R.id.btnDangXuatKhachHang);
        Button btnDoiMK = view.findViewById(R.id.btnDoiMatKhau);



        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
        String tenkh = sharedPreferences.getString("tenkh", "");
        String sdt = sharedPreferences.getString("soDienThoai", "");

        // Hiển thị thông tin trên TextView

        txtTenKh.setText(tenkh);
        txtSdt.setText("0"+sdt);

        btnDangXuatKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DangNhapActivity.class));
                getActivity().finish();
            }
        });

        btnLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LienHeActivity.class));
            }
        });
        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), DoiMatKhauActivity.class));
            }
        });
        return view;


    }
}