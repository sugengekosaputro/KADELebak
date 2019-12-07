package com.inspektorat.kadelebak.view.kade_complaint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_forum.adapter.ContentForumAdapter;

import java.util.List;

public class ContentComplaintAdapter extends RecyclerView.Adapter<ContentComplaintAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;

    public ContentComplaintAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply_forum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (userList.size() > 0) ? userList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
