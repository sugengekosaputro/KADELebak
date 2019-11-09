package com.inspektorat.kadelebak.view.kade_dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintActivity;
import com.inspektorat.kadelebak.view.kade_profile.ProfileActivity;
import com.inspektorat.kadelebak.view.kade_support.SupportActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends Activity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                intent = new Intent(getApplicationContext(), ComplaintActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_notifications:
                intent = new Intent(getApplicationContext(), SupportActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_profile:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                break;
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
    }

}
