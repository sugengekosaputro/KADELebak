package com.inspektorat.kadelebak.view.kade_village.view;

import com.inspektorat.kadelebak.entity.InstitutionEntity;
import com.inspektorat.kadelebak.view.kade_village.entity.Institution;

import java.util.List;

public class VillageView {
    public interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataVillage(List<InstitutionEntity> institutions);
    }

    public interface Detail {
        void showDataVillage(Institution institution);
    }
}
