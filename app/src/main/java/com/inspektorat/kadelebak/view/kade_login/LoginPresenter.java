package com.inspektorat.kadelebak.view.kade_login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.view.kade_login.view.LoginView;


public class LoginPresenter {

    private LoginView view;
    private Context context;
    private MyPreferencesData myPreferencesData;


    public LoginPresenter(LoginView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void checkLogin(String email, String pass) {

        String m = "user@gmail.com";
        String p = "user123";

        if (pass.length() == 0) {
            view.onPasswordValidationError("Password Harus Diisi");
        } else {
            view.removePasswordValidation();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.onEmailValidationError("Email Tidak Valid");
        } else {
            view.removeEmailValidation();
        }

        if (email.equals(m) && pass.equals(p)) {
            myPreferencesData = MyPreferencesData.getInstance(context);
            myPreferencesData.saveData("id",m);
            view.onSuccess();
        } else {
            view.onError("Login Gagal");
        }
    }
}
