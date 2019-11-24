package com.inspektorat.kadelebak.view.kade_login.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.Util;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.tv_back_to_login)
    void onClickBackLogin(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_login, new LoginFragment())
                .commit();
    }
}
