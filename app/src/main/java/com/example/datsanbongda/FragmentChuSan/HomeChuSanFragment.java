package com.example.datsanbongda.FragmentChuSan;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.datsanbongda.ActivityChuSan.ThongTinSanChuSanActivity;
import com.example.datsanbongda.ActivityKhachHang.LienHeActivity;
import com.example.datsanbongda.DAO.SanHomeDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.adapter.San5HomeAdapter;
import com.example.datsanbongda.adapter.San7HomeAdapter;
import com.example.datsanbongda.adapter.San7HomeChuSanAdapter;
import com.example.datsanbongda.model.San5Home;
import com.example.datsanbongda.model.San7Home;

import java.util.ArrayList;


public class HomeChuSanFragment extends Fragment {

    private ArrayList<San7Home> listSan7;
    private ArrayList<San5Home> listSan5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_chu_san, container, false);
        RecyclerView rvSan7Home = view.findViewById(R.id.rvSan7Home);
        RecyclerView rvSan5Home = view.findViewById(R.id.rvSan5Home);
        LinearLayout thongtin = view.findViewById(R.id.thongtinsan);

        LinearLayout lienhe = view.findViewById(R.id.lienhe);
        thongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThongTinSanChuSanActivity.class));
            }
        });
        lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LienHeActivity.class));
            }
        });
        SanHomeDAO sanHomeDAO = new SanHomeDAO(getContext());
        //getList san 7
        LinearLayoutManager linearLayoutManagerSan7 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listSan7 = sanHomeDAO.getDSSan7();
        rvSan7Home.setLayoutManager(linearLayoutManagerSan7);
        San7HomeChuSanAdapter san7HomeAdapter = new San7HomeChuSanAdapter(getContext(), sanHomeDAO, listSan7);
        rvSan7Home.setAdapter(san7HomeAdapter);
        //getList san 5
        LinearLayoutManager linearLayoutManagerSan5 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listSan5 = sanHomeDAO.getDSSan5();
        rvSan5Home.setLayoutManager(linearLayoutManagerSan5);
        San5HomeAdapter san5HomeAdapter = new San5HomeAdapter(getContext(), sanHomeDAO, listSan5);
        rvSan5Home.setAdapter(san5HomeAdapter);

        return view;
    }
}