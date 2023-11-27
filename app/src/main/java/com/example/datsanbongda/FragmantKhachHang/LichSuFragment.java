package com.example.datsanbongda.FragmantKhachHang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datsanbongda.DAO.DanhGiaDAO;
import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.adapter.DanhGiaAdapter;
import com.example.datsanbongda.adapter.LichSuDatSanAdapter;
import com.example.datsanbongda.model.LichSuDatSan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class LichSuFragment extends Fragment {
    private LichSuDatSanDAO lichSuDatSanDAO;
    private ArrayList<LichSuDatSan> list;
    private RecyclerView rvLichSu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        rvLichSu = view.findViewById(R.id.rvLichSu);
        list = new ArrayList<>();
        //data
        lichSuDatSanDAO = new LichSuDatSanDAO(getContext());
        //adapter
        loadData();
        return view;

    }
    private void loadData() {
        list = lichSuDatSanDAO.getDSLichSuGiamDan();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvLichSu.setLayoutManager(linearLayoutManager);
        LichSuDatSanAdapter lichSuDatSanAdapter = new LichSuDatSanAdapter(getContext(), list, lichSuDatSanDAO);
        rvLichSu.setAdapter(lichSuDatSanAdapter);
    }

}