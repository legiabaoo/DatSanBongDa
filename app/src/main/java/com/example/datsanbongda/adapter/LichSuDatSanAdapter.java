package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.ActivityKhachHang.DatChoActivity;
import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.model.LichSuDatSan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        holder.txtThoiGian.setText(list.get(holder.getAdapterPosition()).getThoiGianBatDau()+" - "+list.get(holder.getAdapterPosition()).getThoiGianKetThuc());
        int trangThai = list.get(holder.getAdapterPosition()).getTrangThai();
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
        holder.txtTenSan.setText(list.get(holder.getAdapterPosition()).getTenSan());

        String[] gioBD = list.get(holder.getAdapterPosition()).getThoiGianBatDau().split(":");
        int igioBD = Integer.parseInt(gioBD[0]);
        int iphutBD = Integer.parseInt(gioBD[1]);
        String[] gioKT = list.get(holder.getAdapterPosition()).getThoiGianKetThuc().split(":");
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
        holder.txtNgay.setText(list.get(holder.getAdapterPosition()).getNgay());

        holder.icChiTietVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(holder.getAdapterPosition()).getTrangThai()==1){
                    Intent intent = new Intent(context, DatChoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("San", list.get(holder.getAdapterPosition()).getTenSan());
                    bundle.putString("GioBD", list.get(holder.getAdapterPosition()).getThoiGianBatDau());
                    bundle.putString("GioKT", list.get(holder.getAdapterPosition()).getThoiGianKetThuc());
                    bundle.putString("Thu", layThu(list.get(holder.getAdapterPosition()).getNgay()));
                    bundle.putString("NgayThangNam", list.get(holder.getAdapterPosition()).getNgay());

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }else if(list.get(holder.getAdapterPosition()).getTrangThai()==0){
                    Toast.makeText(context, "Để xem vui lòng chờ xác nhận", Toast.LENGTH_SHORT).show();
                }else if(list.get(holder.getAdapterPosition()).getTrangThai()==2){
                    Toast.makeText(context, "Sân bạn đặt đã thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public String layThu(String ngayThangNam){
        String[] date = ngayThangNam.split("/");
        Calendar calendar = Calendar.getInstance();
        int ngay = Integer.parseInt(date[0]);
        int thang = Integer.parseInt(date[1])-1;
        int nam = Integer.parseInt(date[2]);
        calendar.set(nam, thang, ngay);
        Date date1 = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dayOfWeek = sdf.format(date1);
        return dayOfWeek;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSan, txtGia,txtThoiGian, txtTrangThai, txtNgay;
        ImageView icChiTietVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSan = itemView.findViewById(R.id.txttenSanLichSu);
            txtThoiGian = itemView.findViewById(R.id.txtThoigian);
            txtGia = itemView.findViewById(R.id.txtgiaSan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            icChiTietVe = itemView.findViewById(R.id.icChiTietVe);
            txtNgay = itemView.findViewById(R.id.txtNgayLichSu);
        }
    }
}
