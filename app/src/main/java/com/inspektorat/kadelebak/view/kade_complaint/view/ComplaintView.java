package com.inspektorat.kadelebak.view.kade_complaint.view;

import com.inspektorat.kadelebak.model.User;

import java.util.List;

public class ComplaintView {

    public interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataComplain(List<User> users);
    }

    public interface CreateComplaint {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void renderSpinner();
    }
}
