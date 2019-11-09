package com.inspektorat.kadelebak.view.kade_dashboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_forum.ForumActivity;
import com.inspektorat.kadelebak.view.kade_support.SupportActivity;
import com.inspektorat.kadelebak.view.kade_village.VillageActivity;

import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FiturAdapter extends RecyclerView.Adapter<FiturAdapter.ViewHolder> {

    private Context context;
    private List<String> listFitur;

    public FiturAdapter(Context context, List<String> listFitur) {
        this.context = context;
        this.listFitur = listFitur;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard_fitur, parent, false);
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
        @BindView(R.id.tv_fitur_name)
        TextView name;

        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cv_fitur)
        void onClick() {
            switch (position) {
                case 0:
                    Intent forumIntent = new Intent(itemView.getContext(), ForumActivity.class);
                    forumIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(forumIntent);
                    break;
                case 1:
                    Intent villageIntent = new Intent(itemView.getContext(), VillageActivity.class);
                    villageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(villageIntent);
                    break;
                default:
                    break;
            }
        }
    }
}
