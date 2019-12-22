package com.inspektorat.kadelebak.view.kade_login;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import com.inspektorat.kadelebak.Constant;
import com.inspektorat.kadelebak.data.BasePresenter;
import com.inspektorat.kadelebak.data.MyPreferencesData;
import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.entity.PositionEntity;
import com.inspektorat.kadelebak.entity.UserAuthEntity;
import com.inspektorat.kadelebak.model.BadRequest;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_login.view.LoginView;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPresenter extends BasePresenter {

    private LoginView.login view;
    private LoginView.register register;

    private Context context;
    private MyPreferencesData myPreferencesData;

    private int userId;
    private int employeeId;
    private int roleId;
    private String name;
    private String sectionId;

    public LoginPresenter(LoginView.login view, Context context) {
        this.view = view;
        this.context = context;
    }

    public LoginPresenter(LoginView.register register, Context context){
        this.register = register;
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
        Call<UserAuthEntity> call = this.initService().getDataUser(email, pass);
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

    public void registerAccount(){
        if (register.validateInput()){
            register.removeError(false);
            register.onRegisterSuccess();
        }
    }

    public void getPosition(){
        Call<List<PositionEntity>> call = this.initService().getDataPosition();
        call.enqueue(new Callback<List<PositionEntity>>() {
            @Override
            public void onResponse(Call<List<PositionEntity>> call, Response<List<PositionEntity>> response) {
                List<PositionEntity> pos = response.body();
                if (response.isSuccessful() && pos.size() > 0) {
                    register.renderPosition(pos);
                }
            }

            @Override
            public void onFailure(Call<List<PositionEntity>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getInstitution(){
        Call<List<InstitutionEntity>> call = this.initService().getDataInstitution();
        call.enqueue(new Callback<List<InstitutionEntity>>() {
            @Override
            public void onResponse(Call<List<InstitutionEntity>> call, Response<List<InstitutionEntity>> response) {
                List<InstitutionEntity> ins = response.body();
                if (response.isSuccessful() && ins.size() > 0) {
                    register.renderInstitution(ins);
                }
            }

            @Override
            public void onFailure(Call<List<InstitutionEntity>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private LoginService initService(){
        LoginService service = NetworkClient.getRetrofit()
                .create(LoginService.class);
        return service;
    }
}
