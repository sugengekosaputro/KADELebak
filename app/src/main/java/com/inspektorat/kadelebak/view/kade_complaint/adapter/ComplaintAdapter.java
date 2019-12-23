package com.inspektorat.kadelebak.view.kade_complaint.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintService;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.CommentList;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.ComplaintModel;
import com.inspektorat.kadelebak.view.kade_complaint.presenter.ComplaintPresenter;
import com.inspektorat.kadelebak.view.kade_complaint.view.ComplaintView;
import com.inspektorat.kadelebak.view.kade_complaint.view.ContentComplaintActivity;
import com.inspektorat.kadelebak.view.kade_forum.view.ContentForumActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    Context context;
    List<ComplaintModel> complaintModels;
    MyPreferencesData myPreferencesData;
    ComplaintView.View view;
    ComplaintPresenter presenter;
    int employeeId;
    boolean canDelete;

    public ComplaintAdapter(Context context, List<ComplaintModel> complaintModels, boolean canDelete, ComplaintView.View view) {
        this.context = context;
        this.complaintModels = complaintModels;
        this.canDelete = canDelete;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_complaint, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ComplaintModel complaintModel = complaintModels.get(position);
        myPreferencesData = MyPreferencesData.getInstance(context);
        employeeId = Integer.parseInt(myPreferencesData.getData(Constant.EMPLOYEE_ID));
        String complaintId = String.valueOf(complaintModel.getComplaintId());

        if (complaintModel.isAnonymous()) {
            holder.name.setText(context.getResources().getString(R.string.anonymous));
        } else {
            holder.name.setText(complaintModel.getPublisher().getName());
        }

//        if (complaintModel.isNotify() || complaintModel.getPublisher().getEmployeeId() != employeeId) {
//            int colorBlue = context.getResources().getColor(R.color.blue1);
//            holder.indicator.setVisibility(View.VISIBLE);
//            holder.preview.setTextColor(colorBlue);
//            holder.name.setTextColor(colorBlue);
//            holder.section.setTextColor(colorBlue);
//        }

        if (complaintModel.getCommentList().size() == 0){
            holder.preview.setText(complaintModel.getContent());
        } else {
            CommentList comment = complaintModel.getCommentList().get(complaintModel.getCommentList().size() - 1);
            holder.preview.setText(comment.getComment());
        }
        holder.section.setText(complaintModel.getSectionId().getName());

        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, ContentComplaintActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Constant.COMPLAINT_ID, complaintModel.getComplaintId());
            intent.putExtra("isAnonymous", complaintModel.isAnonymous());
            context.startActivity(intent);
        });

        if (canDelete) {
            holder.layout.setOnLongClickListener(view -> {
                new MaterialAlertDialogBuilder(context, R.style.MyDialog)
                        .setTitle("Peringatan")
                        .setMessage("Anda yakin akan menghapus pengaduan ini ?")
                        .setPositiveButton("Ya", (dialogInterface, i) -> {
                            int id = Integer.parseInt(complaintId);
                            presenter = new ComplaintPresenter(this.view);
                            presenter.deleteComplaintById(id);
                        })
                        .setNegativeButton("Tidak", (dialogInterface, i) -> dialogInterface.dismiss())
                        .setCancelable(false)
                        .show();
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return (complaintModels.size() > 0) ? complaintModels.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item_complaint)
        LinearLayout layout;

        @BindView(R.id.tv_complaint_name)
        TextView name;

        @BindView(R.id.tv_item_complaint_preview)
        TextView preview;

        @BindView(R.id.tv_complaint_section)
        TextView section;

        @BindView(R.id.view_item_indicator_complaint)
        View indicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
