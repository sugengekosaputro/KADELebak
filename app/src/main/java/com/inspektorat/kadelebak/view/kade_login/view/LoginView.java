package com.inspektorat.kadelebak.view.kade_login.view;

public interface LoginView {

    void onEmailValidationError(String msg);
    void onPasswordValidationError(String msg);
    void removeEmailValidation();
    void removePasswordValidation();
    void onError(String msg);
    void onSuccess();
}
