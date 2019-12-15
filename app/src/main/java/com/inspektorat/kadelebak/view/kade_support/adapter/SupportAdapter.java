package com.inspektorat.kadelebak.view.kade_support.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_complaint.adapter.ComplaintAdapter;
import com.inspektorat.kadelebak.view.kade_support.SupportContentActivity;
import com.inspektorat.kadelebak.view.kade_support.SupportModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.ViewHolder> {

    Context context;
    private List<SupportModel> supportModels;

    public SupportAdapter(Context context, List<SupportModel> supportModels) {
        this.context = context;
        this.supportModels = supportModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_support_fitur, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SupportModel model = this.supportModels.get(position);

        holder.name.setText(model.getTitle());
        holder.position = position;

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SupportContentActivity.class);
                i.putExtra("webView", model);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (supportModels.size() > 0) ? supportModels.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_support_name)
        TextView name;

        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
