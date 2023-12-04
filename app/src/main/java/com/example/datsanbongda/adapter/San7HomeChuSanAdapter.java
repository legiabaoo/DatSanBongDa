package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.ActivityKhachHang.ChiTietSanActivity;
import com.example.datsanbongda.DAO.SanHomeDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.model.San7Home;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class San7HomeChuSanAdapter extends RecyclerView.Adapter<San7HomeChuSanAdapter.ViewHolder> {
    private Context context;
    private SanHomeDAO sanHomeDAO;
    private ArrayList<San7Home> list;
    int trangthai;
    int trangthai1 = 0;

    public San7HomeChuSanAdapter(Context context, SanHomeDAO sanHomeDAO, ArrayList<San7Home> list) {
        this.context = context;
        this.sanHomeDAO = sanHomeDAO;
        this.list = list;
    }

    @NonNull
    @Override
    public San7HomeChuSanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.list_chusan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull San7HomeChuSanAdapter.ViewHolder holder, int position) {
        holder.txtTenSanHome.setText(list.get(position).getTenSan());
        trangthai = list.get(position).getTrangThai();
        if(trangthai==0){
            holder.txtTrangThaiHome.setText("Đang hoạt động");
            holder.txtTrangThaiHome.setTextColor(ContextCompat.getColor(context, R.color.colorThanhCong));
        }else if(trangthai==1){
            holder.txtTrangThaiHome.setText("Ngừng hoạt động");
            holder.txtTrangThaiHome.setTextColor(ContextCompat.getColor(context, R.color.colorThatBai));
        }
        holder.btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tensan", list.get(holder.getAdapterPosition()).getTenSan());
                if(list.get(holder.getAdapterPosition()).getLoaiSan()==1){
                    bundle.putString("loaisan", "Sân 7");
                }else if(list.get(holder.getAdapterPosition()).getLoaiSan()==2){
                    bundle.putString("loaisan", "Sân 5");
                }
                if(list.get(holder.getAdapterPosition()).getTrangThai()==0){
                    bundle.putString("trangthai", "Đang hoạt động");
                } else if (list.get(holder.getAdapterPosition()).getTrangThai()==1) {
                    bundle.putString("trangthai", "Ngừng hoạt động");
                }
                bundle.putString("masan","1");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.imgsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                San7Home san =list.get(holder.getAdapterPosition());
                dialogUpdateSan(san);

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
        ImageView imgxoa, imgsua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgsua = itemView.findViewById(R.id.imgEdit);
            imgxoa = itemView.findViewById(R.id.imgDelete);
            txtTenSanHome = itemView.findViewById(R.id.txtTenSanHome);
            txtTrangThaiHome = itemView.findViewById(R.id.txtTrangThaiHome);
            btnXemChiTiet = itemView.findViewById(R.id.btnXemChiTietHome);
        }
    }
    private void dialogUpdateSan(San7Home san7) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chinhsuasan,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        TextInputEditText edtTenSan = view.findViewById(R.id.edtTenSan);
        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        RadioButton radioHoatDong = view.findViewById(R.id.rb_hoatdong);
        RadioButton radioNgungHoatDong = view.findViewById(R.id.rb_ngunghoatdong);
        Button btnCapNhat = view.findViewById(R.id.btnUpdateTrangThai);
        Button btnHuy = view.findViewById(R.id.btnCancelTrangThai);


        //set dữ liệu lên các edt để lấy giá trị cũ của SanPham cần update
        edtTenSan.setText(san7.getTenSan());

        if(san7.getTrangThai() == 0){
            radioHoatDong.setChecked(true);
        }else {
            radioNgungHoatDong.setChecked(true);
        }


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

        //Bắt sự kiện cho btnUpdate
        btnCapNhat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //lấy dữ liệu từ các ô nhập trong dialog
                String tenSan = edtTenSan.getText().toString();

                San7Home s = new San7Home(san7.getMaSan(), tenSan, trangthai1);

                boolean check = sanHomeDAO.updataSan(s);
                if (check){
                    list.clear();
                    list = sanHomeDAO.getDSSan7();
                    notifyDataSetChanged();
                    Toast.makeText(context, "Cập Nhật Thành Công :))", Toast.LENGTH_SHORT).show();
                    //đóng dialog
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Cập Nhật Khum Có Được Huhu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Bắt sự kiện cho btnCancel
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //show dialog khi gọi
        dialog.show();

    }
}
