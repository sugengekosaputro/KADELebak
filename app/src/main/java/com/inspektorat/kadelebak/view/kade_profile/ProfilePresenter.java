package com.inspektorat.kadelebak.view.kade_profile;

import android.util.Log;

import com.inspektorat.kadelebak.data.BasePresenter;
import com.inspektorat.kadelebak.entity.EmployeeEntity;
import com.inspektorat.kadelebak.model.BadRequest;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintService;
import com.inspektorat.kadelebak.view.kade_dashboard.view.DashboardView;
import com.inspektorat.kadelebak.view.kade_login.LoginPresenter;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter extends BasePresenter {

    private ProfileView.view view;
    private DashboardView.Fitur dashboardView;

    public ProfilePresenter(ProfileView.view view) {
        this.view = view;
    }

    public ProfilePresenter(DashboardView.Fitur dashboardView) {
        this.dashboardView = dashboardView;
    }

    public void checkProfileFirst(String empId) {
        Call<EmployeeEntity> call = initService().getEmployeeById(empId);
        call.enqueue(new Callback<EmployeeEntity>() {
            @Override
            public void onResponse(Call<EmployeeEntity> call, Response<EmployeeEntity> response) {
                if (!response.isSuccessful()) {
                    BadRequest badRequest = ProfilePresenter.super.gson.fromJson(
                            response.errorBody().charStream(),BadRequest.class);
                    dashboardView.userNotExist(badRequest.getMessage());
                }
            }

            @Override
            public void onFailure(Call<EmployeeEntity> call, Throwable t) {

            }
        });
    }

    public void getProfile(String empId) {
        Call<EmployeeEntity> call = initService().getEmployeeById(empId);
        call.enqueue(new Callback<EmployeeEntity>() {
            @Override
            public void onResponse(Call<EmployeeEntity> call, Response<EmployeeEntity> response) {
                if (response.isSuccessful()) {
                    view.showDataProfile(response.body());
                } else {
                    BadRequest badRequest = ProfilePresenter.super.gson.fromJson(
                            response.errorBody().charStream(),BadRequest.class);
                    view.onErrorData(badRequest.getMessage());
                }
            }

            @Override
            public void onFailure(Call<EmployeeEntity> call, Throwable t) {
                Log.d("PROFILES", "onFailure: "+t.getMessage());
            }
        });
    }

    private ProfileService initService(){
        ProfileService service = NetworkClient.getRetrofit()
                .create(ProfileService.class);
        return service;
    }
}
