package com.example.datsanbongda.FragmentChuSan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datsanbongda.DAO.LichSuDuyetSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.adapter.LichSuDuyetSanAdapter;
import com.example.datsanbongda.model.LichSuDuyetSan;

import java.util.ArrayList;


public class LichSuChuSanFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lich_su_chu_san, container, false);
        RecyclerView rvDuyetSan = view.findViewById(R.id.rvDuyetSan);

        ArrayList<LichSuDuyetSan> list = new ArrayList<>();
        LichSuDuyetSanDAO lichSuDuyetSanDAO = new LichSuDuyetSanDAO(getContext());
        list = lichSuDuyetSanDAO.getDSDuyetSan();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvDuyetSan.setLayoutManager(linearLayoutManager);
        LichSuDuyetSanAdapter lichSuDuyetSanAdapter = new LichSuDuyetSanAdapter(getContext(), list, lichSuDuyetSanDAO);
        rvDuyetSan.setAdapter(lichSuDuyetSanAdapter);
        return view;
    }
}