package com.example.datsanbongda.FragmentChuSan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datsanbongda.ActivityChuSan.ThongTinSanChuSanActivity;
import com.example.datsanbongda.ActivityKhachHang.LienHeActivity;
import com.example.datsanbongda.DAO.SanHomeDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.adapter.San5HomeAdapter;
import com.example.datsanbongda.adapter.San5HomeChuSanAdapter;
import com.example.datsanbongda.adapter.San7HomeAdapter;
import com.example.datsanbongda.adapter.San7HomeChuSanAdapter;
import com.example.datsanbongda.model.San5Home;
import com.example.datsanbongda.model.San7Home;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class HomeChuSanFragment extends Fragment {

    private ArrayList<San7Home> listSan7;
    private ArrayList<San5Home> listSan5;

    int trangthai1 = 0;
    San7HomeChuSanAdapter san7HomeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_chu_san, container, false);
        RecyclerView rvSan7Home = view.findViewById(R.id.rvSan7Home);
        RecyclerView rvSan5Home = view.findViewById(R.id.rvSan5Home);
        LinearLayout thongtin = view.findViewById(R.id.thongtinsan);
        TextView txtThemsan = view.findViewById(R.id.txtThemSan);

        SanHomeDAO sanHomeDAO = new SanHomeDAO(getContext());
        //getList san 7
        LinearLayoutManager linearLayoutManagerSan7 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listSan7 = sanHomeDAO.getDSSan7();
        rvSan7Home.setLayoutManager(linearLayoutManagerSan7);
        san7HomeAdapter = new San7HomeChuSanAdapter(getContext(), sanHomeDAO, listSan7);
        rvSan7Home.setAdapter(san7HomeAdapter);
        //getList san 5

        LinearLayoutManager linearLayoutManagerSan5 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listSan5 = sanHomeDAO.getDSSan5();
        rvSan5Home.setLayoutManager(linearLayoutManagerSan5);
        San5HomeChuSanAdapter san5HomeAdapter = new San5HomeChuSanAdapter(getContext(), sanHomeDAO, listSan5);
        rvSan5Home.setAdapter(san5HomeAdapter);

        txtThemsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = (HomeChuSanFragment.this).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_add,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                Toast.makeText(getActivity(), "Thêm sân ", Toast.LENGTH_SHORT).show();

                TextInputEditText edtTenSan = view.findViewById(R.id.edtTenSan);
                RadioGroup radioGroup = view.findViewById(R.id.radio_group);
                RadioButton radioHoatDong = view.findViewById(R.id.rb_hoatdong);
                RadioButton radioNgungHoatDong = view.findViewById(R.id.rb_ngunghoatdong);
                Button btnCapNhat = view.findViewById(R.id.btnUpdateTrangThai);
                Button btnHuy = view.findViewById(R.id.btnCancelTrangThai);

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId==R.id.rb_hoatdong){
                            trangthai1 = 0;
                        } else {
                            trangthai1 = 1;
                        }
                    }
                });
                btnCapNhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenSan = edtTenSan.getText().toString();


                        San7Home s = new San7Home();
                        s.setTenSan(tenSan);
                        s.setTrangThai(trangthai1);

                        boolean check = sanHomeDAO.addSan(s);
                        if (check){
                            listSan7.clear();
                            listSan7 = sanHomeDAO.getDSSan7();
                            san7HomeAdapter.notifyDataSetChanged();
                            Toast.makeText(getActivity(), "Thêm Sân Thành Công :))", Toast.LENGTH_SHORT).show();
                            //đóng dialog
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getActivity(), "Thêm Sân Khum Có Được Huhu!", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                dialog.show();

            }

        });
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


        return view;
    }

}