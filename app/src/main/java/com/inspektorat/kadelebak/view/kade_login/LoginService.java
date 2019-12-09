package com.inspektorat.kadelebak.view.kade_login;

import com.inspektorat.kadelebak.entity.UserAuthEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {

    @GET("user/login")
    Call<UserAuthEntity> getDataUser(
            @Query("email") String email,
            @Query("password") String password);
}
