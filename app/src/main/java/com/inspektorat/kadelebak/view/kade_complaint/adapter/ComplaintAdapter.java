package com.inspektorat.kadelebak.view.kade_complaint.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    Context context;
    List<String> listFitur;

    public ComplaintAdapter(Context context, List<String> listFitur) {
        this.context = context;
        this.listFitur = listFitur;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complaint_fitur, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String itemName = listFitur.get(position);
        holder.name.setText(itemName);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return (listFitur.size() > 0) ? listFitur.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_complaint_name)
        TextView name;

        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_complaint)
        void onClick() {
            switch (position) {
                case 0:
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
    }
}
