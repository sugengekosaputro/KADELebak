package com.inspektorat.kadelebak.view.kade_support.view;

import java.util.List;

public class SupportVew {

    public interface Fitur {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataFitur(List<String> listFitur);
    }
}
