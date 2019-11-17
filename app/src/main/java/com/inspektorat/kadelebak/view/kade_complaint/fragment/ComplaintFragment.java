package com.inspektorat.kadelebak.view.kade_complaint.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_complaint.adapter.ComplaintAdapter;
import com.inspektorat.kadelebak.view.kade_complaint.presenter.ComplaintPresenter;
import com.inspektorat.kadelebak.view.kade_complaint.view.ComplaintView;
import com.inspektorat.kadelebak.view.kade_dashboard.DashboardActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintFragment extends Fragment implements ComplaintView.Fitur {

    @BindView(R.id.rv_complaint_fitur)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    ComplaintAdapter complaintAdapter;
    ComplaintPresenter presenter;

    public ComplaintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);
        ButterKnife.bind(this, view);
        initToolbar();
        initPresenter();
        setRecyclerview();
        return view;
    }

    private void initToolbar() {
        toolbarTitle.setText(getResources().getString(R.string.title_complaint));
    }

    private void initPresenter() {
        presenter = new ComplaintPresenter(this);
        presenter.setFitur(Objects.requireNonNull(getActivity()).getApplicationContext().getResources().getStringArray(R.array.complaint_item));
    }

    private void setRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext()));
        recyclerView.setAdapter(complaintAdapter);
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
        complaintAdapter = new ComplaintAdapter(getActivity().getApplicationContext(), listFitur);
        complaintAdapter.notifyDataSetChanged();
    }
}
