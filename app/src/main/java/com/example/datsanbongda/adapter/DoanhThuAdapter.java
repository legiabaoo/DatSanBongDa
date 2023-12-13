package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.ActivityKhachHang.DatChoActivity;
import com.example.datsanbongda.DAO.DoanhThuDAO;
import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DoanhThu;
import com.example.datsanbongda.model.LichSuDatSan;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DoanhThuAdapter extends RecyclerView.Adapter<DoanhThuAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DoanhThu> list;
    private DoanhThuDAO doanhThuDAO;
    private int giaSanSang, giaSanToi, maLoaiSan;
    private DbHelper dbHelper;

    public DoanhThuAdapter(Context context, ArrayList<DoanhThu> list, DoanhThuDAO doanhThuDAO) {
        this.context = context;
        this.list = list;
        this.doanhThuDAO = doanhThuDAO;
    }

    @NonNull
    @Override
    public DoanhThuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dbHelper = new DbHelper(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_doanhthu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoanhThuAdapter.ViewHolder holder, int position) {
        String maSan = String.valueOf(list.get(holder.getAdapterPosition()).getMaSan());
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursorSAN = sqLiteDatabase.rawQuery("SELECT maLoaiSan FROM SAN WHERE maSan=?", new String[]{maSan});
        if(cursorSAN.moveToFirst()){
            maLoaiSan = cursorSAN.getInt(0);
        }
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT tienSanSang, tienSanToi FROM LOAISAN WHERE maLoaiSan=?", new String[]{String.valueOf(maLoaiSan)});
        if (cursor.moveToFirst()){
            giaSanSang = cursor.getInt(0);
            giaSanToi = cursor.getInt(1);
        }


        holder.txtThoiGian.setText(list.get(holder.getAdapterPosition()).getThoiGianBatDau()+"" +
                " - "+list.get(holder.getAdapterPosition()).getThoiGianKetThuc()+" - "+
                list.get(holder.getAdapterPosition()).getNgay());

        int trangThai = list.get(holder.getAdapterPosition()).getTrangThai();
        if (trangThai == 3) {
            holder.txtDaThanhToan.setVisibility(View.GONE);
            holder.txtHuy.setVisibility(View.GONE);
            int tienCoc = Integer.parseInt(context.getResources().getString(R.string.tienCoc));
            int giaDu = list.get(holder.getAdapterPosition()).getTienSan()-tienCoc;
            holder.txtGia.setText("Đã trả: "+dinhdangtien(tienCoc+giaDu));
            holder.txtGiaDu.setText("Còn lại: "+dinhdangtien(0));
        } else if (trangThai==1){
            holder.txtDaThanhToan.setVisibility(View.VISIBLE);
            holder.txtHuy.setVisibility(View.VISIBLE);
            int tienCoc = Integer.parseInt(context.getResources().getString(R.string.tienCoc));
            holder.txtGia.setText("Đã trả: "+dinhdangtien(tienCoc));
            int giaDu = list.get(holder.getAdapterPosition()).getTienSan()-tienCoc;
            holder.txtGiaDu.setText("Còn lại: "+dinhdangtien(giaDu));
        }

        holder.txtTenSan.setText(list.get(holder.getAdapterPosition()).getTenSan());

        String[] gioBD = list.get(holder.getAdapterPosition()).getThoiGianBatDau().split(":");
        int igioBD = Integer.parseInt(gioBD[0]);
        int iphutBD = Integer.parseInt(gioBD[1]);
        String[] gioKT = list.get(holder.getAdapterPosition()).getThoiGianKetThuc().split(":");
        int igioKT = Integer.parseInt(gioKT[0]);
        int iphutKT = Integer.parseInt(gioKT[1]);


        holder.txtNgay.setText(String.valueOf(list.get(holder.getAdapterPosition()).getNgayDat()));
        holder.txtDaThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trangThai=3;
                boolean check= doanhThuDAO.updateDoanhThu(list.get(holder.getAdapterPosition()).getMaVe(), trangThai);
                if (check){
//                    Toast.makeText(context, "Thành Công", Toast.LENGTH_SHORT).show();
                    holder.txtDaThanhToan.setVisibility(View.GONE);
                    holder.txtHuy.setVisibility(View.GONE);
                    int tienCoc = Integer.parseInt(context.getResources().getString(R.string.tienCoc));
                    int giaDu = list.get(holder.getAdapterPosition()).getTienSan()-tienCoc;
                    holder.txtGia.setText("Đã trả: "+dinhdangtien(tienCoc+giaDu));
                    holder.txtGiaDu.setText("Còn lại: "+dinhdangtien(0));
                }else{
//                    Toast.makeText(context, "Thất Bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSan, txtGia,txtThoiGian, txtTrangThai, txtNgay, txtDaThanhToan, txtHuy, txtGiaDu;
        ImageView icChiTietVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSan = itemView.findViewById(R.id.txttenSanLichSu);
            txtThoiGian = itemView.findViewById(R.id.txtThoigian);
            txtGia = itemView.findViewById(R.id.txtgiaSan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            icChiTietVe = itemView.findViewById(R.id.icChiTietVe);
            txtNgay = itemView.findViewById(R.id.txtNgayDatLichSu);
            txtDaThanhToan = itemView.findViewById(R.id.txtThanhToanDu);
            txtHuy = itemView.findViewById(R.id.txtHuy);
            txtGiaDu = itemView.findViewById(R.id.txtgiaSanDu);
        }
    }
    private String dinhdangtien(int amount) {
        // Tạo một đối tượng NumberFormat với Locale.getDefault() để định dạng theo ngôn ngữ và quốc gia của thiết bị
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        // Chuyển đổi int thành định dạng tiền tệ và trả về kết quả
        return currencyFormatter.format(amount);
    }
}
