package com.inspektorat.kadelebak.view.kade_village.presenter;

import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_village.entity.Institution;
import com.inspektorat.kadelebak.view.kade_village.service.VillageService;
import com.inspektorat.kadelebak.view.kade_village.view.VillageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VillagePresenter {

    private VillageView.View view;

    public VillagePresenter(VillageView.View view) {
        this.view = view;
    }

    public void initialize(){
        this.getData();
    }

    private void getData() {
        VillageService service = NetworkClient.getRetrofit()
                .create(VillageService.class);

        Call<List<Institution>> call = service.getAllInstitution();
        call.enqueue(new Callback<List<Institution>>() {
            @Override
            public void onResponse(Call<List<Institution>> call, Response<List<Institution>> response) {
                view.showDataVillage(response.body());
            }

            @Override
            public void onFailure(Call<List<Institution>> call, Throwable t) {
                view.showError("Koneksi Error");
            }
        });
    }
}
