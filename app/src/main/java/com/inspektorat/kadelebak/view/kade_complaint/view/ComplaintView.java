package com.inspektorat.kadelebak.view.kade_complaint.view;

import java.util.List;

public class ComplaintView {

    public interface Fitur {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataFitur(List<String> listFitur);
    }
}
