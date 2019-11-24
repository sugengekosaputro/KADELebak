package com.inspektorat.kadelebak.view.kade_login.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_dashboard.DashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    @BindView(R.id.btn_login)
    MaterialButton btnLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.tv_forgot_password)
    void onClickForgotPass() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_login, new ForgetPasswordFragment())
                .commit();
    }

    @OnClick(R.id.btn_login)
    public void onClickLogin() {
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
        Util.animate(getActivity());
    }
}
