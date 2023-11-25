package com.example.datsanbongda.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datsanbongda.DAO.SanHomeDAO;
import com.example.datsanbongda.R;
import com.example.datsanbongda.model.San5Home;
import com.example.datsanbongda.model.San7Home;

import java.util.ArrayList;

public class San5HomeAdapter extends RecyclerView.Adapter<San5HomeAdapter.ViewHolder> {
    private Context context;
    private SanHomeDAO sanHomeDAO;
    private ArrayList<San5Home> list;

    public San5HomeAdapter(Context context, SanHomeDAO sanHomeDAO, ArrayList<San5Home> list) {
        this.context = context;
        this.sanHomeDAO = sanHomeDAO;
        this.list = list;
    }

    @NonNull
    @Override
    public San5HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.listview_san, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull San5HomeAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
