package com.inspektorat.kadelebak.view.kade_dashboard.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_dashboard.adapter.FiturAdapter;
import com.inspektorat.kadelebak.view.kade_dashboard.presenter.DashboardPresenter;
import com.inspektorat.kadelebak.view.kade_dashboard.view.DashboardView;
import com.inspektorat.kadelebak.view.kade_splash.SplashActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements DashboardView.Fitur {


    @BindView(R.id.ll_top_dashboard)
    LinearLayout llTopDashboard;
    @BindView(R.id.rv_dashboard_fitur)
    RecyclerView recyclerView;

    DashboardPresenter presenter;
    FiturAdapter fiturAdapter;
    MyPreferencesData myPreferencesData;
    @BindView(R.id.tv_fragment_home_name)
    TextView tvName;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        myPreferencesData = MyPreferencesData.getInstance(getActivity());
        tvName.setText(myPreferencesData.getData(Constant.NAME));
        initPresenter();
        setRecyclerview();
        return view;
    }

    private void initPresenter() {
        presenter = new DashboardPresenter(this);
        presenter.setFitur(Objects.requireNonNull(getActivity()).getApplicationContext().getResources().getStringArray(R.array.feature_item));
    }

    private void setRecyclerview() {
        recyclerView.setLayoutManager(new GridLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext(), 2));
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
        fiturAdapter = new FiturAdapter(getActivity().getApplicationContext(), listFitur);
        fiturAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_icon_notification)
    void onClickIcon() {
        new MaterialAlertDialogBuilder(getActivity(), R.style.MyDialog)
                .setTitle("KADE Lebak")
                .setMessage("Anda yakin akan keluar ?")
                .setPositiveButton("Keluar", (dialogInterface, i) -> this.logout())
                .setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.dismiss())
                .setCancelable(false)
                .show();
    }

    private void logout() {
        myPreferencesData.clearData();
        Intent intent = new Intent(getActivity().getApplicationContext(), SplashActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
