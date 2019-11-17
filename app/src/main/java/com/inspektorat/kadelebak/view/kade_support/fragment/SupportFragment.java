package com.inspektorat.kadelebak.view.kade_support.fragment;


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
import com.inspektorat.kadelebak.view.kade_dashboard.DashboardActivity;
import com.inspektorat.kadelebak.view.kade_support.adapter.SupportAdapter;
import com.inspektorat.kadelebak.view.kade_support.presenter.SupportPresenter;
import com.inspektorat.kadelebak.view.kade_support.view.SupportVew;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupportFragment extends Fragment implements SupportVew.Fitur {

    @BindView(R.id.rv_support_fitur)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    MaterialToolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    SupportAdapter supportAdapter;
    SupportPresenter presenter;

    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        ButterKnife.bind(this, view);
        initPresenter();
        setRecyclerview();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(1000);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initPresenter() {
        presenter = new SupportPresenter(this);
        presenter.setFitur(Objects.requireNonNull(getActivity()).getApplicationContext().getResources().getStringArray(R.array.support_item));
    }

    private void setRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(Objects.requireNonNull(getActivity()).getApplicationContext()));
        recyclerView.setAdapter(supportAdapter);
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
        supportAdapter = new SupportAdapter(getActivity().getApplicationContext(), listFitur);
        supportAdapter.notifyDataSetChanged();
    }
}
