package com.inspektorat.kadelebak.view.kade_login;

import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.entity.PositionEntity;
import com.inspektorat.kadelebak.entity.UserAuthEntity;
import com.inspektorat.kadelebak.model.SuccessMessage;
import com.inspektorat.kadelebak.view.kade_complaint.model.create_page.ComplaintReplyModel;
import com.inspektorat.kadelebak.view.kade_login.model.RegisterModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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

    @Multipart
    @POST("employee/save")
    Call<SuccessMessage> submit(
            @Part("name") RequestBody name,
            @Part("bornPlace") RequestBody bornPlace,
            @Part("dob") RequestBody dob,
            @Part("gender") RequestBody gender,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("unit") RequestBody unit,
            @Part("positionId") RequestBody positionId,
            @Part("institutionId") RequestBody institutionId,
            @Part("roleId") RequestBody roleId,
            @Part("sectionId") RequestBody sectionId,
            @Part MultipartBody.Part image,
            @Part("verfied") RequestBody verfied,
            @Part("password") RequestBody password,
            @Part("statusUpload") RequestBody statusUpload,
            @Part("statusVerified") RequestBody statusVerified);

    @Multipart
    @POST("employee/update/{id}")
    Call<SuccessMessage> updateProfile(
            @Path("id") String id,
            @Part("name") RequestBody name,
            @Part("bornPlace") RequestBody bornPlace,
            @Part("dob") RequestBody dob,
            @Part("gender") RequestBody gender,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("unit") RequestBody unit,
            @Part("positionId") RequestBody positionId,
            @Part("institutionId") RequestBody institutionId,
            @Part("roleId") RequestBody roleId,
            @Part("sectionId") RequestBody sectionId,
            @Part MultipartBody.Part image,
            @Part("verfied") RequestBody verfied,
            @Part("password") RequestBody password,
            @Part("statusUpload") RequestBody statusUpload,
            @Part("statusVerified") RequestBody statusVerified);
}
