package com.inspektorat.kadelebak.view.kade_profile;

import com.inspektorat.kadelebak.entity.EmployeeEntity;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintService;
import com.inspektorat.kadelebak.view.kade_profile.view.ProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    private ProfileView.view view;

    public Presenter(ProfileView.view view) {
        this.view = view;
    }

    public void getProfile(String empId) {
        Call<EmployeeEntity> call = initService().getEmployeeById(empId);
        call.enqueue(new Callback<EmployeeEntity>() {
            @Override
            public void onResponse(Call<EmployeeEntity> call, Response<EmployeeEntity> response) {
                view.showDataProfile(response.body());
            }

            @Override
            public void onFailure(Call<EmployeeEntity> call, Throwable t) {

            }
        });
    }

    private ProfileService initService(){
        ProfileService service = NetworkClient.getRetrofit()
                .create(ProfileService.class);
        return service;
    }
}
