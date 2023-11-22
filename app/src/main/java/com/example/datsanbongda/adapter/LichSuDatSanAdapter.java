package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.txtThoiGian.setText(list.get(position).getThoiGianBatDau());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenSan, txtGia,txtThoiGian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenSan = itemView.findViewById(R.id.txttenSan);
            txtThoiGian = itemView.findViewById(R.id.txtThoigian);
        }
    }
}
