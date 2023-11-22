package com.example.datsanbongda;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.adapter.DanhGiaAdapter;
import com.example.datsanbongda.adapter.LichSuDatSanAdapter;
import com.example.datsanbongda.model.LichSuDatSan;

import java.util.ArrayList;


public class LichSuFragment extends Fragment {
    private ArrayList<LichSuDatSan> list;
    private LichSuDatSanDAO lichSuDatSanDAO;
    private RecyclerView rvLichSu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        rvLichSu = view.findViewById(R.id.rvLichSu);

        lichSuDatSanDAO = new LichSuDatSanDAO(getContext());
        loadData();

        return view;
    }
    private void loadData() {
        list = lichSuDatSanDAO.getDSLichSu();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvLichSu.setLayoutManager(linearLayoutManager);
        LichSuDatSanAdapter lichSuDatSanAdapter = new LichSuDatSanAdapter(getContext(), list, lichSuDatSanDAO);
        rvLichSu.setAdapter(lichSuDatSanAdapter);
    }
}