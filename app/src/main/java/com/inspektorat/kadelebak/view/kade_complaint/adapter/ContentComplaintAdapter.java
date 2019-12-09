package com.inspektorat.kadelebak.view.kade_complaint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.CommentList;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.ComplaintModel;
import com.inspektorat.kadelebak.view.kade_forum.adapter.ContentForumAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentComplaintAdapter extends RecyclerView.Adapter<ContentComplaintAdapter.ViewHolder> {

    private Context context;
    private List<CommentList> commentLists;
    private String employeeId;
    private String publisher;
    private boolean isAnonymous;

    public ContentComplaintAdapter(Context context, List<CommentList> commentLists, String employeeId, String publisher, boolean isAnonymous) {
        this.context = context;
        this.commentLists = commentLists;
        this.employeeId = employeeId;
        this.publisher = publisher;
        this.isAnonymous = isAnonymous;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply_forum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentList commentList = this.commentLists.get(position);

        String sender = String.valueOf(commentList.getSender().getEmployeeId());


//        if (isAnonymous && employeeId.equals(String.valueOf(commentList.getSender().getEmployeeId()))) {
//            holder.name.setText(context.getResources().getString(R.string.anonymous));
//        } else {
//            holder.name.setText(commentList.getSender().getName());
//        }

        String username = commentList.getSender().getName();
        if (employeeId.equals(String.valueOf(commentList.getSender().getEmployeeId())) && employeeId.equals(publisher)) {
            username = "Anda";
        }

        if (publisher.equals(sender) && isAnonymous){
            username = "anonym";
        }

        holder.name.setText(username);
        holder.content.setText(commentList.getComment());
    }

    @Override
    public int getItemCount() {
        return (commentLists.size() > 0) ? commentLists.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_reply_forum_name)
        TextView name;

        @BindView(R.id.tv_item_reply_forum_comment)
        TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
