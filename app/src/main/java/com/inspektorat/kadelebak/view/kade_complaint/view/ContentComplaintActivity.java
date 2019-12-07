package com.inspektorat.kadelebak.view.kade_complaint.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_complaint.adapter.ContentComplaintAdapter;
import com.inspektorat.kadelebak.view.kade_forum.adapter.ContentForumAdapter;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentComplaintActivity extends AppCompatActivity {

    ContentComplaintAdapter adapter;

    @BindView(R.id.rv_forum_reply)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_complaint);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Konten Pengaduan");
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle object = intent.getExtras();
        List<User> user = (List<User>) object.getSerializable(Constant.SERIALIZABLE_COMPLAINT);
        setRecyclerview(user);
    }

    private void setRecyclerview(List<User> user) {
        LinearLayoutManager ll = new LinearLayoutManager(getApplicationContext());
        ll.setReverseLayout(true);
        recyclerview.setLayoutManager(ll);

        adapter = new ContentComplaintAdapter(getApplicationContext(), user);
        recyclerview.setAdapter(adapter);
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
