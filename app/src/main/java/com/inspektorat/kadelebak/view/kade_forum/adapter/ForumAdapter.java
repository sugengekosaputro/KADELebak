package com.inspektorat.kadelebak.view.kade_forum.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumModel;
import com.inspektorat.kadelebak.view.kade_forum.view.ContentForumActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {

    private Context context;
    private List<ForumModel> forumModelList;
    MyPreferencesData myPreferencesData;

    public ForumAdapter(Context context, List<ForumModel> forumModelList) {
        this.context = context;
        this.forumModelList = forumModelList;
        this.myPreferencesData = MyPreferencesData.getInstance(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_forum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ForumModel forumModel = forumModelList.get(position);
        int size = forumModel.getCommentList().size();
        String name = Util.nameBuilderCapitalized(forumModel.getPublisher().getName());

        if (size > 0) {
            holder.comments.setText(context.getResources().getString(R.string.x_comments, size));
        } else {
            holder.comments.setText(context.getResources().getString(R.string.no_comments));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContentForumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constant.FORUM_ID, forumModel.getForumId());
                context.startActivity(intent);
            }
        });

        if (myPreferencesData.getData(Constant.EMPLOYEE_ID).equals(String.valueOf(forumModel.getPublisher().getEmployeeId()))){
            holder.name.setTextColor(context.getResources().getColor(R.color.blue1));
        }

        if (forumModel.getPublisher().getSection() != null) {
            name = name + " (" + Util.nameBuilderCapitalized(forumModel.getPublisher().getSection().getName()) + ")";
        }

        ColorGenerator generator = ColorGenerator.DEFAULT;
        int color= generator.getColor(forumModel.getForumId());

        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(Util.imageInitial(name), color, 20);

        holder.icon.setImageDrawable(drawable);
        holder.name.setText(name);
        holder.content.setText(forumModel.getContent());
    }

    @Override
    public int getItemCount() {
        return (forumModelList.size() > 0) ? forumModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cv_item_forum)
        CardView cardView;

        @BindView(R.id.tv_item_forum_employee_name)
        TextView name;

        @BindView(R.id.tv_item_forum_employee_content)
        TextView content;

        @BindView(R.id.tv_item_forum_employee_comments)
        TextView comments;

        @BindView(R.id.image_icon)
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
