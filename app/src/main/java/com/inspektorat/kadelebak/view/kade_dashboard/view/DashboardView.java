package com.inspektorat.kadelebak.view.kade_dashboard.view;

import java.util.List;

public class DashboardView {

    public interface Fitur {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataFitur(List<String> listFitur);
    }
}
