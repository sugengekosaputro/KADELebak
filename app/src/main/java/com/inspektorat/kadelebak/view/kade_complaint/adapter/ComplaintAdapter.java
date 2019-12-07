package com.inspektorat.kadelebak.view.kade_complaint.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_complaint.view.ContentComplaintActivity;
import com.inspektorat.kadelebak.view.kade_forum.view.ContentForumActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    Context context;
    List<User> listFitur;

    public ComplaintAdapter(Context context, List<User> listFitur) {
        this.context = context;
        this.listFitur = listFitur;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complaint, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.layout.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(context, ContentComplaintActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            bundle.putSerializable(Constant.SERIALIZABLE_COMPLAINT, (Serializable) listFitur);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (listFitur.size() > 0) ? listFitur.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item_complaint)
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
