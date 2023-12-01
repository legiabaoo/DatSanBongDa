package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.ActivityKhachHang.ChiTietSanActivity;
import com.example.datsanbongda.ActivityKhachHang.DatSanActivity;
import com.example.datsanbongda.DAO.SanHomeDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.model.San7Home;

import java.util.ArrayList;

public class San7HomeAdapter extends RecyclerView.Adapter<San7HomeAdapter.ViewHolder> {
    private Context context;
    private SanHomeDAO sanHomeDAO;
    private ArrayList<San7Home> list;

    public San7HomeAdapter(Context context, SanHomeDAO sanHomeDAO, ArrayList<San7Home> list) {
        this.context = context;
        this.sanHomeDAO = sanHomeDAO;
        this.list = list;
    }

    @NonNull
    @Override
    public San7HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.listview_san, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull San7HomeAdapter.ViewHolder holder, int position) {
        holder.txtTenSanHome.setText(list.get(position).getTenSan());
        int trangthai = list.get(position).getTrangThai();
        if(trangthai==0){
            holder.txtTrangThaiHome.setText("Đang hoạt động");
        }else if(trangthai==1){
            holder.txtTrangThaiHome.setText("Ngừng hoạt động");
            holder.txtTrangThaiHome.setTextColor(ContextCompat.getColor(context, R.color.colorThatBai));
        }
        holder.btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tensan", list.get(position).getTenSan());
                if(list.get(position).getLoaiSan()==1){
                    bundle.putString("loaisan", "Sân 7");
                }else if(list.get(position).getLoaiSan()==2){
                    bundle.putString("loaisan", "Sân 5");
                }
                if(list.get(position).getTrangThai()==0){
                    bundle.putString("trangthai", "Đang hoạt động");
                } else if (list.get(position).getTrangThai()==1) {
                    bundle.putString("trangthai", "Ngừng hoạt động");
                }
                bundle.putString("masan","1");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSanHome, txtTrangThaiHome;
        Button btnXemChiTiet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSanHome = itemView.findViewById(R.id.txtTenSanHome);
            txtTrangThaiHome = itemView.findViewById(R.id.txtTrangThaiHome);
            btnXemChiTiet = itemView.findViewById(R.id.btnXemChiTietHome);
        }
    }
}
