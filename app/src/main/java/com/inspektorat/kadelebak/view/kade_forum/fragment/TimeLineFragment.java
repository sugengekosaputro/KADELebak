package com.inspektorat.kadelebak.view.kade_forum.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_forum.adapter.ForumAdapter;
import com.inspektorat.kadelebak.view.kade_forum.presenter.ForumPresenter;
import com.inspektorat.kadelebak.view.kade_forum.view.ForumView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeLineFragment extends Fragment implements ForumView.View {

    ForumPresenter presenter;
    ForumAdapter adapter;
    @BindView(R.id.rv_forum_all)
    RecyclerView recyclerview;

    public TimeLineFragment() {
        // Required empty public constructor
        presenter = new ForumPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_line, container, false);
        ButterKnife.bind(this, view);
        presenter.initialize();
        setRecyclerview();
        return view;
    }

    private void setRecyclerview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerview.setAdapter(adapter);
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
    public void showDataVillage(List<User> userList) {
        adapter = new ForumAdapter(getActivity().getApplicationContext(), userList);
        adapter.notifyDataSetChanged();
    }
}
