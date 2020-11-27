package com.inspektorat.kadelebak.view.kade_dashboard;

import com.inspektorat.kadelebak.view.kade_dashboard.model.Attribute;
import com.inspektorat.kadelebak.view.kade_dashboard.model.CoronaEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoronaService {

    @GET("indonesia/provinsi")
    Call<List<CoronaEntity>> getCoronaBanten();
}
