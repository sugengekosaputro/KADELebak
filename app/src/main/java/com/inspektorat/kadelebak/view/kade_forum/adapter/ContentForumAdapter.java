package com.inspektorat.kadelebak.view.kade_forum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_forum.model.CommentList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentForumAdapter extends RecyclerView.Adapter<ContentForumAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;
    private List<CommentList> commentList;

    public ContentForumAdapter(Context context, List<CommentList> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply_forum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentList commentList = this.commentList.get(position);
        holder.name.setText(commentList.getSender().getName());
        holder.content.setText(commentList.getComment());
    }

    @Override
    public int getItemCount() {
        return (commentList.size() > 0) ? commentList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_reply_forum_name)
        TextView name;

        @BindView(R.id.tv_item_reply_forum_comment)
        TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
