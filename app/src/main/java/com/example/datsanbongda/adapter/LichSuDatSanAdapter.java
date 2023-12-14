package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.ActivityKhachHang.DatChoActivity;
import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.LichSuDatSan;
import com.example.datsanbongda.model.LichSuDuyetSan;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LichSuDatSanAdapter extends RecyclerView.Adapter<LichSuDatSanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<LichSuDatSan> list;
    private LichSuDatSanDAO lichSuDatSanDAO;
    private int giaSanSang, giaSanToi, maLoaiSan;
    private DbHelper dbHelper;

    public LichSuDatSanAdapter(Context context, ArrayList<LichSuDatSan> list, LichSuDatSanDAO lichSuDatSanDAO) {
        this.context = context;
        this.list = list;
        this.lichSuDatSanDAO = lichSuDatSanDAO;
    }

    @NonNull
    @Override
    public LichSuDatSanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dbHelper = new DbHelper(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichsudatsan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuDatSanAdapter.ViewHolder holder, int position) {
        String maSan = String.valueOf(list.get(holder.getAdapterPosition()).getMaSan());
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursorSAN = sqLiteDatabase.rawQuery("SELECT maLoaiSan FROM SAN WHERE maSan=?", new String[]{maSan});
        if(cursorSAN.moveToFirst()){
            maLoaiSan = cursorSAN.getInt(0);
        }
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT tienSanSang, tienSanToi FROM LOAISAN WHERE maLoaiSan=?"
                ,new String[]{String.valueOf(maLoaiSan)});
        if(cursor.moveToFirst()){
            giaSanSang = cursor.getInt(0);
            giaSanToi = cursor.getInt(1);
        }
//        Cursor cursorGiaSang = sqLiteDatabase.rawQuery("SELECT LOAISAN.tienSanSang, LOAISAN.tienSanToi FROM DATCHO " +
//                "INNER JOIN SAN ON DATCHO.maSan = SAN.maSan " +
//                "INNER JOIN KHACHHANG ON DATCHO.maKhachHang = KHACHHANG.maKhachHang " +
//                "INNER JOIN LOAISAN ON LOAISAN.maLoaiSan = SAN.maLoaiSan " +
//                "WHERE LOAISAN.maLoaiSan=? AND " +
//                "datetime(?)<datetime(thoiGianBatDau) AND datetime(thoiGianBatDau)<datetime(?)", new String[]{String.valueOf(maLoaiSan), "6:00", "18:00"});
//        Cursor cursorGiaToi = sqLiteDatabase.rawQuery("SELECT LOAISAN.tienSanSang, LOAISAN.tienSanToi FROM DATCHO " +
//                "INNER JOIN SAN ON DATCHO.maSan = SAN.maSan " +
//                "INNER JOIN KHACHHANG ON DATCHO.maKhachHang = KHACHHANG.maKhachHang " +
//                "INNER JOIN LOAISAN ON LOAISAN.maLoaiSan = SAN.maLoaiSan " +
//                "WHERE LOAISAN.maLoaiSan=? AND " +
//                "datetime(?)>datetime(thoiGianBatDau) AND datetime(thoiGianBatDau)>datetime(?)", new String[]{String.valueOf(maLoaiSan), "6:00", "18:00"});
//        if (cursorGiaSang.getCount()>0){
//            cursorGiaSang.moveToFirst();
//            giaSanSang = cursorGiaSang.getInt(0);
//        }else if(cursorGiaToi.getCount()>0){
//            cursorGiaToi.moveToFirst();
//            giaSanSang = cursorGiaToi.getInt(1);
//        }

        holder.txtThoiGian.setText(list.get(holder.getAdapterPosition()).getThoiGianBatDau()+"" +
                " - "+list.get(holder.getAdapterPosition()).getThoiGianKetThuc()+" - "+
                list.get(holder.getAdapterPosition()).getNgay());
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
        int tienSan = 0;
        int gioLe = 0;
        int tienSanLe =0;
        if(igioBD>=18 || igioBD<6 ){
            if(igioKT>6 && igioKT<18){
                gioLe = igioKT-6;
                tienSanLe = gioLe*giaSanSang;
                tienSan = (igioKT-igioBD-gioLe)*giaSanToi+tienSanLe;
                if(iphutKT-iphutBD==30){
                    tienSan+=giaSanToi/2;
                }else if(iphutKT-iphutBD==-30){
                    tienSan-=giaSanToi/2;
                }
            } else if (igioKT<18 && igioKT>6) {
                gioLe = 18-igioKT;
                tienSanLe = gioLe*giaSanSang;
                tienSan = (igioKT-igioBD-gioLe)*giaSanToi+tienSanLe;
                if(iphutKT-iphutBD==30){
                    tienSan+=giaSanToi/2;
                }else if(iphutKT-iphutBD==-30){
                    tienSan-=giaSanToi/2;
                }
            }else {
                tienSan = (igioKT-igioBD)*giaSanToi;
                if(iphutKT-iphutBD==30){
                    tienSan+=giaSanToi/2;
                }else if(iphutKT-iphutBD==-30){
                    tienSan-=giaSanToi/2;
                }
            }

        } else if (igioBD>=6 && igioBD<18) {
            if(igioKT<6){
                gioLe = 6-igioKT;
                tienSanLe = gioLe*giaSanToi;
            } else if (igioKT>18) {
                gioLe = igioKT - 18;
                tienSanLe = gioLe*giaSanToi;
            }
            tienSan = (igioKT-igioBD-gioLe)*giaSanSang+tienSanLe;
            if(iphutKT-iphutBD==30){
                tienSan+=giaSanSang/2;
            }else if(iphutKT-iphutBD==-30){
                tienSan-=giaSanSang/2;
            }
        }
        holder.txtGia.setText(dinhdangtien(tienSan));
        holder.txtNgay.setText(String.valueOf(list.get(holder.getAdapterPosition()).getNgayDat()));

        int finalTienSan = tienSan;
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
                    bundle.putInt("TienSan", finalTienSan);

                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }else if(list.get(holder.getAdapterPosition()).getTrangThai()==0){
                    Toast.makeText(context, "Để xem vui lòng chờ xác nhận", Toast.LENGTH_SHORT).show();
                }else if(list.get(holder.getAdapterPosition()).getTrangThai()==2){
                    Toast.makeText(context, "Sân bạn đặt đã thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.lick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh Báo");
                builder.setIcon(R.drawable.ic_warning_24);
                builder.setMessage("Bạn có muốn xóa lịch sử này không ?");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int mave = list.get(holder.getAdapterPosition()).getMaVe();
                        boolean check = lichSuDatSanDAO.deleteLichSu(mave);
                        if(check){
                            Toast.makeText(context, "Xóa Lịch Sử Thành Công!", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list = lichSuDatSanDAO.getDSLichSuGiamDan();
                            notifyItemRemoved(holder.getAdapterPosition());

                        }else {
                            Toast.makeText(context, "Xóa khum có được", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog =builder.create();
                alertDialog.show();


                return true;
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
        RelativeLayout lick;
        ImageView icChiTietVe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lick = itemView.findViewById(R.id.lick);
            txtTenSan = itemView.findViewById(R.id.txttenSanLichSu);
            txtThoiGian = itemView.findViewById(R.id.txtThoigian);
            txtGia = itemView.findViewById(R.id.txtgiaSan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            icChiTietVe = itemView.findViewById(R.id.icChiTietVe);
            txtNgay = itemView.findViewById(R.id.txtNgayDatLichSu);
        }
    }
    private String dinhdangtien(int amount) {
        // Tạo một đối tượng NumberFormat với Locale.getDefault() để định dạng theo ngôn ngữ và quốc gia của thiết bị
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault());

        // Chuyển đổi int thành định dạng tiền tệ và trả về kết quả
        return currencyFormatter.format(amount);
    }
    public void updateList(ArrayList<LichSuDatSan> newlist) {
        list.clear();
        list.addAll(newlist);
        notifyDataSetChanged();
    }
}
