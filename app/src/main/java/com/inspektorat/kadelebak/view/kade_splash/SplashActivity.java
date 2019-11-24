package com.inspektorat.kadelebak.view.kade_splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {


    @BindView(R.id.tv_splash)
    TextView tvSplash;

    private long delay = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fade_in, 0);

        goWaitAndAnimateEnd();
    }

    private void goWaitAndAnimateEnd() {
        tvSplash.postDelayed(() -> {
            Intent I = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(I);
            Util.animate(this);
            finish();
        }, delay);
    }
}
