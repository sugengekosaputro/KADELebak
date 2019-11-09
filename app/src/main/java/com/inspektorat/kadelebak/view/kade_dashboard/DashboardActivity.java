package com.inspektorat.kadelebak.view.kade_dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintActivity;
import com.inspektorat.kadelebak.view.kade_dashboard.fragment.HomeFragment;
import com.inspektorat.kadelebak.view.kade_dashboard.fragment.TestFragment;
import com.inspektorat.kadelebak.view.kade_profile.ProfileActivity;
import com.inspektorat.kadelebak.view.kade_support.SupportActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.botom_nav)
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new HomeFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.navigation_dashboard:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new TestFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.navigation_notifications:
                return true;
            case R.id.navigation_profile:
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }

}
