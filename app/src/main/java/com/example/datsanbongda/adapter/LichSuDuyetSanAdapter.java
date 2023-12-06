package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.DAO.DoanhThuDAO;
import com.example.datsanbongda.DAO.LichSuDatSanDAO;
import com.example.datsanbongda.DAO.LichSuDuyetSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DoanhThu;
import com.example.datsanbongda.model.LichSuDatSan;
import com.example.datsanbongda.model.LichSuDuyetSan;

import java.util.ArrayList;

public class LichSuDuyetSanAdapter extends RecyclerView.Adapter<LichSuDuyetSanAdapter.ViewHolder> {
    private Context context;
    private DbHelper dbHelper;
    private ArrayList<LichSuDuyetSan> list;
    private LichSuDuyetSanDAO lichSuDuyetSanDAO;
    private LichSuDatSanDAO lichSuDatSanDAO;
    private DoanhThuDAO doanhThuDAO;
    private int giaSanSang, giaSanToi, maLoaiSan;

    public LichSuDuyetSanAdapter(Context context, ArrayList<LichSuDuyetSan> list, LichSuDuyetSanDAO lichSuDuyetSanDAO) {
        this.context = context;
        this.list = list;
        this.lichSuDuyetSanDAO = lichSuDuyetSanDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        doanhThuDAO = new DoanhThuDAO(context);
        lichSuDatSanDAO = new LichSuDatSanDAO(context);
        dbHelper = new DbHelper(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.list_duyetsan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String maSan = String.valueOf(list.get(holder.getAdapterPosition()).getMaSan());
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursorSAN = sqLiteDatabase.rawQuery("SELECT maLoaiSan FROM SAN WHERE maSan=?", new String[]{maSan});
        if (cursorSAN.moveToFirst()) {
            maLoaiSan = cursorSAN.getInt(0);
        }
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT tienSanSang, tienSanToi FROM LOAISAN WHERE maLoaiSan=?", new String[]{String.valueOf(maLoaiSan)});
        if (cursor.moveToFirst()) {
            giaSanSang = cursor.getInt(0);
            giaSanToi = cursor.getInt(1);
        }

        holder.txtThoiGianDuyetSan.setText(list.get(holder.getAdapterPosition()).getThoiGianBatDau() + "" +
                " - " + list.get(holder.getAdapterPosition()).getThoiGianKetThuc() + " - " +
                list.get(holder.getAdapterPosition()).getNgay());

//        if(trangThai==0){
//            holder.txtTrangThai.setText("Chờ xác nhận");
//            holder.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.colorChoXacNhan));
//        } else if (trangThai==1) {
//            holder.txtTrangThai.setText("Thành công");
//            holder.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.colorThanhCong));
//        } else if (trangThai==2) {
//            holder.txtTrangThai.setText("Thất bại");
//            holder.txtTrangThai.setTextColor(ContextCompat.getColor(context, R.color.colorThatBai));
//        }
        holder.txtTenSanDuyetSan.setText(list.get(holder.getAdapterPosition()).getTenSan());

        String[] gioBD = list.get(holder.getAdapterPosition()).getThoiGianBatDau().split(":");
        int igioBD = Integer.parseInt(gioBD[0]);
        int iphutBD = Integer.parseInt(gioBD[1]);
        String[] gioKT = list.get(holder.getAdapterPosition()).getThoiGianKetThuc().split(":");
        int igioKT = Integer.parseInt(gioKT[0]);
        int iphutKT = Integer.parseInt(gioKT[1]);

        int tienSan = (igioKT - igioBD) * giaSanSang;
        if (tienSan > 1000) {
            tienSan = tienSan / 1000;
        }

        if (iphutKT - iphutBD == 30) {
            tienSan += giaSanSang / 1000 / 2;
        } else if (iphutKT - iphutBD == -30) {
            tienSan -= giaSanSang / 1000 / 2;
        }
        int finalTienSan = tienSan*1000;
        holder.txtGiaSanDuyetSan.setText(tienSan + ".000");
        holder.txtNgayDatDuyetSan.setText(String.valueOf(list.get(holder.getAdapterPosition()).getNgayDat()));
        holder.txtDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trangThai = 1;
                LichSuDuyetSan lichSuDuyetSan = new LichSuDuyetSan(list.get(holder.getAdapterPosition()).getMaVe(), trangThai);
                boolean checkDS = lichSuDuyetSanDAO.dongY(lichSuDuyetSan);
                LichSuDatSan lichSuDatSan = new LichSuDatSan(list.get(holder.getAdapterPosition()).getMaVe(), trangThai);
                boolean checkLS = lichSuDatSanDAO.ThayDoiTrangThai(lichSuDatSan);
                String thoiGianBatDau = list.get(holder.getAdapterPosition()).getThoiGianBatDau();
                String thoiGianKetThuc = list.get(holder.getAdapterPosition()).getThoiGianKetThuc();
                String ngay = list.get(holder.getAdapterPosition()).getNgay();
                String ngayDat = list.get(holder.getAdapterPosition()).getNgayDat();
                int tienSanDoanhThu = finalTienSan;
                int maChuSan = list.get(holder.getAdapterPosition()).getMaChuSan();
                int maKhachHang = list.get(holder.getAdapterPosition()).getMaKhachHang();
                DoanhThu doanhThu = new DoanhThu(thoiGianBatDau, thoiGianKetThuc, ngay, ngayDat, tienSanDoanhThu, trangThai, Integer.parseInt(maSan), maChuSan, maKhachHang);
                boolean checkDT = doanhThuDAO.themDoanhThu(doanhThu);
                if (checkDS && checkLS && checkDT) {
                    Toast.makeText(context, "Bạn đồng ý thành công", Toast.LENGTH_SHORT).show();
                    lichSuDuyetSanDAO.getDSDuyetSan(); // Giả sử có một phương thức trong DAO để lấy danh sách mới
                    ArrayList<LichSuDuyetSan> updatedList = lichSuDuyetSanDAO.getDSDuyetSan();
                    updateList(updatedList);
                } else {
                    Toast.makeText(context, "Bạn đồng ý thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.txtTuChoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trangThai = 2;
                LichSuDuyetSan lichSuDuyetSan = new LichSuDuyetSan(list.get(holder.getAdapterPosition()).getMaVe(), trangThai);
                boolean checkDS = lichSuDuyetSanDAO.dongY(lichSuDuyetSan);
                LichSuDatSan lichSuDatSan = new LichSuDatSan(list.get(holder.getAdapterPosition()).getMaVe(), trangThai);
                boolean checkLS = lichSuDatSanDAO.ThayDoiTrangThai(lichSuDatSan);
                if (checkDS && checkLS) {
                    Toast.makeText(context, "Bạn từ chối thành công", Toast.LENGTH_SHORT).show();
                    lichSuDuyetSanDAO.getDSDuyetSan(); // Giả sử có một phương thức trong DAO để lấy danh sách mới
                    ArrayList<LichSuDuyetSan> updatedList = lichSuDuyetSanDAO.getDSDuyetSan();
                    updateList(updatedList);
                } else {
                    Toast.makeText(context, "Bạn từ chối thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(ArrayList<LichSuDuyetSan> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSanDuyetSan, txtGiaSanDuyetSan,
                txtThoiGianDuyetSan, txtDongY, txtTuChoi, txtNgayDatDuyetSan
                , txtMa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSanDuyetSan = itemView.findViewById(R.id.txtTenSanDuyetSan);
            txtTuChoi = itemView.findViewById(R.id.txtTuChoi);
            txtDongY = itemView.findViewById(R.id.txtDongY);
            txtGiaSanDuyetSan = itemView.findViewById(R.id.txtGiaSanDuyetSan);
            txtThoiGianDuyetSan = itemView.findViewById(R.id.txtThoiGianDuyetSan);
            txtNgayDatDuyetSan = itemView.findViewById(R.id.txtNgayDatDuyetSan);
            txtMa = itemView.findViewById(R.id.txtMa);
        }

    }

}
