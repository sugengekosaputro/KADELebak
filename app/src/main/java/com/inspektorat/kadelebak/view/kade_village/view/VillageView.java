package com.inspektorat.kadelebak.view.kade_village.view;

import com.inspektorat.kadelebak.view.kade_village.entity.Institution;

import java.util.List;

public class VillageView {
    public interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataVillage(List<Institution> institutions);
    }

    public interface Detail {
        void showDataVillage(Institution institution);
    }
}
