package com.inspektorat.kadelebak.view.kade_support;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.inspektorat.kadelebak.R;

import butterknife.ButterKnife;

public class SupportFragment extends Fragment {

    public SupportFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support, container, false);
        MaterialToolbar toolbar = view.findViewById(R.id.appbarlayout_support);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        return view;
    }
}
