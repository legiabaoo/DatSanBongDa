package com.example.datsanbongda.FragmentChuSan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.datsanbongda.ActivityChuSan.ThongTinSanChuSanActivity;
import com.example.datsanbongda.R;


public class HomeChuSanFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_chu_san, container, false);
        LinearLayout thongtinsan = view.findViewById(R.id.thongtinsan);
        thongtinsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThongTinSanChuSanActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}