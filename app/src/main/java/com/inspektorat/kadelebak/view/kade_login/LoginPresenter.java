package com.inspektorat.kadelebak.view.kade_login;

import android.content.Context;
import android.util.Patterns;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.data.BasePresenter;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.entity.UserAuthEntity;
import com.inspektorat.kadelebak.model.BadRequest;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_login.view.LoginView;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter extends BasePresenter {

    private LoginView view;
    private ProfileView.view profileView;

    private Context context;
    private MyPreferencesData myPreferencesData;

    private int userId;
    private int employeeId;
    private int roleId;
    private String name;
    private String sectionId;

    public LoginPresenter(LoginView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void checkLogin(String email, String pass) {

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

        this.retrofitAuth(email, pass);
    }

    private void retrofitAuth(String email, String pass){
        LoginService service = NetworkClient.getRetrofit()
                .create(LoginService.class);

        Call<UserAuthEntity> call = service.getDataUser(email, pass);
        call.enqueue(new Callback<UserAuthEntity>() {
            @Override
            public void onResponse(Call<UserAuthEntity> call, Response<UserAuthEntity> response) {
                if (response.isSuccessful()) {
                    UserAuthEntity user = response.body();

                    userId = user.getUserId();
                    employeeId = user.getEmployee().getEmployeeId();
                    roleId = user.getEmployee().getRole().getRoleId();
                    name = user.getEmployee().getName();

                    if (user.getEmployee().getSection() == null){
                        sectionId = "";
                    } else {
                        sectionId = String.valueOf(user.getEmployee().getSection().getSectionId());
                    }

                    setSharedPreferences(
                            userId,
                            employeeId,
                            roleId,
                            sectionId,
                            name
                    );
                    view.onSuccess();
                } else {
                    BadRequest badRequest = LoginPresenter.super.gson.fromJson(
                            response.errorBody().charStream(),BadRequest.class);
                    view.onError(badRequest.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserAuthEntity> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void setSharedPreferences(int userId, int employeeId, int roleId, String sectionId, String name){
        myPreferencesData = MyPreferencesData.getInstance(context);

        String sec;
        if (sectionId.length() == 0) {
            sec = "";
        } else {
            sec = sectionId;
        }

        myPreferencesData.saveData(Constant.USER_ID, String.valueOf(userId));
        myPreferencesData.saveData(Constant.EMPLOYEE_ID, String.valueOf(employeeId));
        myPreferencesData.saveData(Constant.ROLE_ID, String.valueOf(roleId));
        myPreferencesData.saveData(Constant.SECTION_ID, sec);
        myPreferencesData.saveData(Constant.NAME, name);
    }
}
