package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.model.LichSuDatSan;

import java.util.ArrayList;

public class LichSuDatSanAdapter extends RecyclerView.Adapter<LichSuDatSanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LichSuDatSan> list;
    private LichSuDatSanDAO lichSuDatSanDAO;

    public LichSuDatSanAdapter(Context context, ArrayList<LichSuDatSan> list, LichSuDatSanDAO lichSuDatSanDAO) {
        this.context = context;
        this.list = list;
        this.lichSuDatSanDAO = lichSuDatSanDAO;
    }

    @NonNull
    @Override
    public LichSuDatSanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichsudatsan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuDatSanAdapter.ViewHolder holder, int position) {
        holder.txtThoiGian.setText(list.get(position).getThoiGianBatDau()+" - "+list.get(position).getThoiGianKetThuc());
        int trangThai = list.get(position).getTrangThai();
        if(trangThai==0){
            holder.txtTrangThai.setText("Chờ xác nhận");
            holder.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.colorChoXacNhan));
        } else if (trangThai==1) {
            holder.txtTrangThai.setText("Thành công");
            holder.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.colorThanhCong));
        } else if (trangThai==2) {
            holder.txtTrangThai.setText("Thất bại");
            holder.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.colorThatBai));
        }
        holder.txtTenSan.setText(list.get(position).getTenSan());

        String[] gioBD = list.get(position).getThoiGianBatDau().split(":");
        int igioBD = Integer.parseInt(gioBD[0]);
        int iphutBD = Integer.parseInt(gioBD[1]);
        String[] gioKT = list.get(position).getThoiGianKetThuc().split(":");
        int igioKT = Integer.parseInt(gioKT[0]);
        int iphutKT = Integer.parseInt(gioKT[1]);

        int tienSan = (igioKT-igioBD)*150000;
        if(tienSan>1000){
            tienSan = tienSan/1000;
        }

        if(iphutKT-iphutBD==30){
            tienSan+=150/2;
        }else if(iphutKT-iphutBD==-30){
            tienSan-=150/2;
        }
        holder.txtGia.setText(String.valueOf(tienSan+".000"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSan, txtGia,txtThoiGian, txtTrangThai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSan = itemView.findViewById(R.id.txttenSan);
            txtThoiGian = itemView.findViewById(R.id.txtThoigian);
            txtGia = itemView.findViewById(R.id.txtgiaSan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
        }
    }
}
