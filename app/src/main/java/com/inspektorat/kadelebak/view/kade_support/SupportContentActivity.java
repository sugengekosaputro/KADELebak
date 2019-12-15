package com.inspektorat.kadelebak.view.kade_support;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.inspektorat.kadelebak.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SupportContentActivity extends AppCompatActivity {

    @BindView(R.id.wv_support_content)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_content);
        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        SupportModel model = (SupportModel) getIntent().getSerializableExtra("webView");

        webView.loadData(model.getContent(),"text/html",null);
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
