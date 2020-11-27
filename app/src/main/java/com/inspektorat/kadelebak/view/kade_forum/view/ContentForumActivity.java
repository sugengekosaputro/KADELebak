package com.inspektorat.kadelebak.view.kade_forum.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.android.material.textfield.TextInputLayout;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_forum.adapter.ContentForumAdapter;
import com.inspektorat.kadelebak.view.kade_forum.model.CommentList;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumModel;
import com.inspektorat.kadelebak.view.kade_forum.presenter.ForumPresenter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContentForumActivity extends AppCompatActivity implements ForumView.ContentForum {

    ContentForumAdapter adapter;
    ForumPresenter presenter;
    Context context = this;

    MyPreferencesData myPreferencesData = MyPreferencesData.getInstance(context);
    int forumId;
    String employeeId;

    @BindView(R.id.rv_content_forum_reply)
    RecyclerView recyclerview;
    @BindView(R.id.tv_content_forum_name)
    TextView tvName;
    @BindView(R.id.tv_content_forum_content)
    TextView tvContent;
    @BindView(R.id.tv_content_forum_counter)
    TextView tvCommentar;

    @BindView(R.id.ll_btn_send_reply_forum)
    LinearLayout btnSendReply;
    @BindView(R.id.edt_content_forum_reply)
    TextInputLayout edtContent;
    @BindView(R.id.sv_content_forum)
    ScrollView svContentForum;
    @BindView(R.id.image_icon)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_forum);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Konten Forum");
        ButterKnife.bind(this);

        employeeId = myPreferencesData.getData(Constant.EMPLOYEE_ID);
        forumId = getIntent().getIntExtra(Constant.FORUM_ID, 0);

        presenter = new ForumPresenter(this, context);
        presenter.getRetrofitById(forumId);

        svContentForum.post(() -> svContentForum.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private void setRecyclerview(List<CommentList> commentList) {
        LinearLayoutManager ll = new LinearLayoutManager(getApplicationContext());
        ll.setReverseLayout(true);
        ll.setStackFromEnd(true);
        recyclerview.setLayoutManager(ll);

        Collections.reverse(commentList);
        adapter = new ContentForumAdapter(getApplicationContext(), commentList);
        adapter.notifyDataSetChanged();
        recyclerview.setAdapter(adapter);
    }

    @OnClick(R.id.ll_btn_send_reply_forum)
    void sendReply() {
        presenter.replyForum(this.forumId, edtContent.getEditText().getText().toString(), this.employeeId);
        Util.hideSoftKeyboard(this);
    }

    @Override
    public void onReplySuccess(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        edtContent.getEditText().getText().clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showDataForum(ForumModel forumModel) {
        int size = forumModel.getCommentList().size();

        String name = forumModel.getPublisher().getName();
        tvName.setText(name);
        tvContent.setText(forumModel.getContent());
        tvCommentar.setText(String.valueOf(forumModel.getCommentList().size()));

        ColorGenerator generator = ColorGenerator.DEFAULT;
        int color= generator.getColor(forumModel.getForumId());

        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(Util.imageInitial(name), color, 20);

        image.setImageDrawable(drawable);

        if (size > 0) {
            tvCommentar.setText(context.getResources().getString(R.string.x_comments, size));
        } else {
            tvCommentar.setText(context.getResources().getString(R.string.no_comments));
        }

        setRecyclerview(forumModel.getCommentList());
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorValidationEnabled(boolean enabled) {
        edtContent.setErrorEnabled(enabled);
    }

    @Override
    public void setErrorValidationMessage(String message) {
        edtContent.setError(message);
    }

    @Override
    public void
    notifyForum() {
        presenter.getRetrofitById(this.forumId);
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
}
