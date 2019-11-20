package com.inspektorat.kadelebak.view.kade_dashboard;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintFragment;
import com.inspektorat.kadelebak.view.kade_dashboard.fragment.HomeFragment;
import com.inspektorat.kadelebak.view.kade_profile.ProfileFragment;
import com.inspektorat.kadelebak.view.kade_support.SupportFragment;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {

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
                        .replace(R.id.contentContainer, new ComplaintFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.navigation_notifications:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new SupportFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.navigation_profile:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
        }
        return false;
    };

    @BindView(R.id.botom_nav)
    BottomNavigationView bottomNavigationView;

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
