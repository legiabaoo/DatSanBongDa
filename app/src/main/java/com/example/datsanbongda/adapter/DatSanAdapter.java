package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.DAO.DatSanDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.database.DbHelper;
import com.example.datsanbongda.model.DoanhThu;

import java.util.ArrayList;

public class DatSanAdapter extends RecyclerView.Adapter<DatSanAdapter.ViewHolder> {
    private ArrayList<DoanhThu> list;
    private DatSanDAO datSanDAO;
    private Context context;

    public DatSanAdapter(ArrayList<DoanhThu> list, DatSanDAO datSanDAO, Context context) {
        this.list = list;
        this.datSanDAO = datSanDAO;
        this.context = context;
    }

    @NonNull
    @Override
    public DatSanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_datsan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatSanAdapter.ViewHolder holder, int position) {
        holder.txtGio.setText(list.get(holder.getAdapterPosition()).getThoiGianBatDau()+" - "
                +list.get(holder.getAdapterPosition()).getThoiGianKetThuc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtGio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGio = itemView.findViewById(R.id.txtGioItemDS);
        }
    }
}
