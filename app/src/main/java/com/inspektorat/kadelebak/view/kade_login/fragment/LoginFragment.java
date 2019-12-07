package com.inspektorat.kadelebak.view.kade_login.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.inspektorat.kadelebak.R;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_dashboard.DashboardActivity;
import com.inspektorat.kadelebak.view.kade_login.LoginPresenter;
import com.inspektorat.kadelebak.view.kade_login.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment implements LoginView {

    LoginPresenter presenter;

    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    @BindView(R.id.btn_login)
    MaterialButton btnLogin;
    @BindView(R.id.edt_login_password)
    TextInputLayout edtPassword;
    @BindView(R.id.edt_login_email)
    TextInputLayout edtEmail;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        presenter = new LoginPresenter(this, getContext());
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
        presenter.checkLogin(edtEmail.getEditText().getText().toString(), edtPassword.getEditText().getText().toString());
    }

    @Override
    public void onEmailValidationError(String msg) {
        edtEmail.setError(msg);
    }

    @Override
    public void onPasswordValidationError(String msg) {
        edtPassword.setError(msg);
    }

    @Override
    public void removeEmailValidation() {
        edtEmail.setErrorEnabled(false);
    }

    @Override
    public void removePasswordValidation() {
        edtPassword.setErrorEnabled(false);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
        Util.animate(getActivity());
    }
}
