package com.inspektorat.kadelebak.view.kade_forum.view;

import com.inspektorat.kadelebak.model.User;

import java.util.List;

public class ForumView {

    public interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataVillage(List<User> userList);
    }
}
