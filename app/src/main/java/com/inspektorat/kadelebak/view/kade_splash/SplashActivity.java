package com.inspektorat.kadelebak.view.kade_splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_dashboard.DashboardActivity;
import com.inspektorat.kadelebak.view.kade_login.LoginActivity;
import com.inspektorat.kadelebak.view.kade_splash.presenter.SplashPresenter;
import com.inspektorat.kadelebak.view.kade_splash.view.SplashView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    private long delay = 500;

    SplashPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fade_in, 0);

        presenter = new SplashPresenter(this,getApplicationContext());
    }

    @Override
    public void redirectActivity() {
        goWaitAndAnimateEnd(new DashboardActivity());
    }

    @Override
    public void onLogin() {
        goWaitAndAnimateEnd(new LoginActivity());
    }

    private void goWaitAndAnimateEnd(Activity page) {
        ivSplash.postDelayed(() -> {
            Intent I = new Intent(SplashActivity.this, page.getClass());
            startActivity(I);
            Util.animate(this);
            finish();
        }, delay);
    }

}
