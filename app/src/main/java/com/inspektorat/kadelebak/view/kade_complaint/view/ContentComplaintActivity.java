package com.inspektorat.kadelebak.view.kade_complaint.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_complaint.adapter.ContentComplaintAdapter;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.CommentList;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.ComplaintModel;
import com.inspektorat.kadelebak.view.kade_complaint.presenter.ComplaintPresenter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentComplaintActivity extends AppCompatActivity implements ComplaintView.ContentComplaint {

    ContentComplaintAdapter adapter;
    ComplaintPresenter presenter;
    Context context = this;

    MyPreferencesData myPreferencesData = MyPreferencesData.getInstance(context);
    int complaintId;
    String employeeId;
    boolean isAnonymous;

    @BindView(R.id.tv_content_complaint_name)
    TextView tvName;
    @BindView(R.id.tv_content_complaint_content)
    TextView tvContent;
    @BindView(R.id.tv_content_complaint_counter)
    TextView tvCounter;
    @BindView(R.id.rv_content_complaint_reply)
    RecyclerView recyclerview;
    @BindView(R.id.edt_content_complaint_reply)
    TextInputLayout edtComplaintReply;
    @BindView(R.id.ll_btn_send_reply_complaint)
    LinearLayout ll_btn_send_complaint;
    @BindView(R.id.tv_content_complaint_date)
    TextView tvDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_complaint);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Konten Pengaduan");
        ButterKnife.bind(this);

        employeeId = myPreferencesData.getData(Constant.EMPLOYEE_ID);
        complaintId = getIntent().getIntExtra(Constant.COMPLAINT_ID, 0);
        isAnonymous = getIntent().getBooleanExtra("isAnonymous",false);

        presenter = new ComplaintPresenter(this, context);
        presenter.getComplaintById(complaintId);
    }

    private void setRecyclerview(List<CommentList> commentLists, String publisher, boolean isAnonymous) {
        if (commentLists.size() > 0) {
            recyclerview.setVisibility(View.VISIBLE);
            LinearLayoutManager ll = new LinearLayoutManager(getApplicationContext());
            ll.setReverseLayout(true);
            ll.setStackFromEnd(true);
            recyclerview.setLayoutManager(ll);
            recyclerview.smoothScrollToPosition(commentLists.size() - 1);

            Collections.reverse(commentLists);
            adapter = new ContentComplaintAdapter(context, commentLists, employeeId, publisher, isAnonymous);
            adapter.notifyDataSetChanged();
            recyclerview.setAdapter(adapter);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setErrorValidationMessage(String message) {
        edtComplaintReply.setError(message);
    }

    @Override
    public void setErrorValidationEnabled(boolean enabled) {
        edtComplaintReply.setErrorEnabled(enabled);
    }

    @OnClick(R.id.ll_btn_send_reply_complaint)
    void sendReply() {
        presenter.replyForum(this.complaintId, edtComplaintReply.getEditText().getText().toString(), this.employeeId);
        Util.hideSoftKeyboard(this);
    }

    @Override
    public void onReplySuccess(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        edtComplaintReply.getEditText().getText().clear();
        presenter.getComplaintById(complaintId);
    }

    @Override
    public void showDataComplaint(ComplaintModel complaintModel) {
        int size = complaintModel.getCommentList().size();
        String publisher = String.valueOf(complaintModel.getPublisher().getEmployeeId());

        if (isAnonymous) {
            tvName.setText(context.getResources().getString(R.string.anonymous));
        } else {
            tvName.setText(complaintModel.getPublisher().getName());
        }

        tvContent.setText(complaintModel.getContent());
        tvDate.setText(complaintModel.getDateTimeCreated());

        if (size > 0) {
            tvCounter.setText(context.getResources().getString(R.string.x_comments, size));
        } else {
            tvCounter.setText(context.getResources().getString(R.string.no_comments));
        }

        setRecyclerview(complaintModel.getCommentList(), publisher, isAnonymous);
    }

    @Override
    public void notifyForum() {
        presenter.getComplaintById(this.complaintId);
    }
}
