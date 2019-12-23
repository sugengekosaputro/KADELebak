package com.inspektorat.kadelebak.view.kade_login;

import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.entity.PositionEntity;
import com.inspektorat.kadelebak.entity.UserAuthEntity;
import com.inspektorat.kadelebak.model.SuccessMessage;
import com.inspektorat.kadelebak.view.kade_complaint.model.create_page.ComplaintReplyModel;
import com.inspektorat.kadelebak.view.kade_login.model.RegisterModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    @GET("user/login")
    Call<UserAuthEntity> getDataUser(
            @Query("email") String email,
            @Query("password") String password);

    @GET("position")
    Call<List<PositionEntity>> getDataPosition();

    @GET("institution")
    Call<List<InstitutionEntity>> getDataInstitution();

    @POST("employee")
    Call<SuccessMessage> submitRegister(@Body RegisterModel registerModel);
}
