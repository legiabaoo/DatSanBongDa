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
import com.example.datsanbongda.model.San7Home;

import java.util.ArrayList;

public class San7HomeAdapter extends RecyclerView.Adapter<San7HomeAdapter.ViewHolder> {
    private Context context;
    private SanHomeDAO sanHomeDAO;
    private ArrayList<San7Home> list;

    public San7HomeAdapter(Context context, SanHomeDAO sanHomeDAO, ArrayList<San7Home> list) {
        this.context = context;
        this.sanHomeDAO = sanHomeDAO;
        this.list = list;
    }

    @NonNull
    @Override
    public San7HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.listview_san, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull San7HomeAdapter.ViewHolder holder, int position) {

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
