package com.inspektorat.kadelebak.view.kade_profile;

import com.inspektorat.kadelebak.entity.EmployeeEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileService {

    @GET("employee/{id}")
    Call<EmployeeEntity> getEmployeeById(@Path("id") String id);
}
