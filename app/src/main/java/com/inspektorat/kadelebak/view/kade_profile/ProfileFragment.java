package com.inspektorat.kadelebak.view.kade_profile;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.kade_dashboard.DashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

//    @BindView(R.id.toolbar)
//    MaterialToolbar toolbar;
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        ButterKnife.bind(this, view);
        initToolbar();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initToolbar() {
  //      toolbarTitle.setText(getResources().getString(R.string.title_profile));
    }

}
