package com.inspektorat.kadelebak.view.kade_dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintFragment;
import com.inspektorat.kadelebak.view.kade_dashboard.fragment.HomeFragment;
import com.inspektorat.kadelebak.view.kade_profile.ProfileFragment;
import com.inspektorat.kadelebak.view.kade_support.fragment.SupportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new HomeFragment())
                        .commit();
                return true;
            case R.id.navigation_complaint:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new ComplaintFragment())
                        .commit();
                return true;
            case R.id.navigation_notifications:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new SupportFragment())
                        .commit();
                return true;
            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new ProfileFragment())
                        .commit();
                return true;
        }
        return false;
    };

    @BindView(R.id.botom_nav)
    BottomNavigationView bottomNavigationView;

    MyPreferencesData myPreferencesData = MyPreferencesData.getInstance(this);
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toast.makeText(getApplicationContext(), "Hello : "+myPreferencesData.getData("id"),Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, new HomeFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit..", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
