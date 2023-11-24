package com.example.datsanbongda.FragmantKhachHang;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.datsanbongda.ActivityKhachHang.LienHeActivity;
import com.example.datsanbongda.R;

public class CaNhanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ca_nhan, container, false);
Button btnLienHe = view.findViewById(R.id.btnLienHeChuSan);
        TextView txtTenKh = view.findViewById(R.id.txtTenKHCaNhan);
        TextView txtSdt = view.findViewById(R.id.txtSdtKH);
        Bundle bundle = getArguments();
        if(bundle != null){
            String ten = bundle.getString("hoten","");
            String sdt = bundle.getString("sdt","");
            txtTenKh.setText(ten);
            txtSdt.setText(sdt);
        }
btnLienHe.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    startActivity(new Intent(getActivity(), LienHeActivity.class));
    }
});
        return view;


    }
}