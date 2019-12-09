package com.inspektorat.kadelebak.view.kade_village.service;

import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.view.kade_village.entity.Institution;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VillageService {

    @GET("institution")
    Call<List<InstitutionEntity>> getAllInstitution();
}
