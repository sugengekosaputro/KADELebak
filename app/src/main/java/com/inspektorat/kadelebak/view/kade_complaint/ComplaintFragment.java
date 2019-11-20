package com.inspektorat.kadelebak.view.kade_complaint;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.MaterialToolbar;
import com.inspektorat.kadelebak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintFragment extends Fragment {


    public ComplaintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complaint, container, false);
        MaterialToolbar toolbar = view.findViewById(R.id.appbarlayout_complaint);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        return view;
    }

}
