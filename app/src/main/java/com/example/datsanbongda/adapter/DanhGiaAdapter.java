package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.datsanbongda.DAO.DanhGiaDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.model.DanhGia;

import java.util.ArrayList;

public class DanhGiaAdapter extends RecyclerView.Adapter<DanhGiaAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DanhGia> list;
    private DanhGiaDAO danhGiaDAO;

    public DanhGiaAdapter(Context context, ArrayList<DanhGia> list, DanhGiaDAO danhGiaDAO) {
        this.context = context;
        this.list = list;
        this.danhGiaDAO = danhGiaDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_danhgia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTen.setText(list.get(position).getTenND());
        holder.txtDanhGia.setText(list.get(position).getDanhGia());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtDanhGia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtDanhGia = itemView.findViewById(R.id.txtDanhGia);
        }
    }
}
