package com.inspektorat.kadelebak.view.kade_dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_dashboard.fragment.HomeFragment;
import com.inspektorat.kadelebak.view.kade_dashboard.adapter.FiturAdapter;
import com.inspektorat.kadelebak.view.kade_dashboard.presenter.DashboardPresenter;
import com.inspektorat.kadelebak.view.kade_dashboard.view.DashboardView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements DashboardView.Fitur{

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
                return true;
            case R.id.navigation_notifications:
                return true;
            case R.id.navigation_profile:
                return true;
        }
        return false;
    };

    @BindView(R.id.botom_nav)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.rv_dashboard_fitur)
    RecyclerView recyclerView;

    DashboardPresenter presenter;
    FiturAdapter fiturAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        initPresenter();
        setRecyclerview();
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }

    private void initPresenter() {
        presenter = new DashboardPresenter(this);
        presenter.setFitur(getApplicationContext().getResources().getStringArray(R.array.feature_item));
    }

    private void setRecyclerview() {
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(fiturAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showDataFitur(List<String> listFitur) {
        fiturAdapter = new FiturAdapter(getApplicationContext(), listFitur);
        fiturAdapter.notifyDataSetChanged();
    }
}
