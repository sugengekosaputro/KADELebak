package com.inspektorat.kadelebak.view.kade_forum.view;

import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumModel;

import java.util.List;

public class ForumView {

    public interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void showDataForum(List<ForumModel> forumModelList);
    }

    public interface ContentForum {
        void showError(String message);
        void setErrorValidationMessage(String message);
        void setErrorValidationEnabled(boolean enabled);
        void onReplySuccess(String message);
        void showDataForum(ForumModel forumModel);
        void notifyForum();
    }

    public interface CreateForum {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void setErrorValidationMessage(String message);
        void setErrorValidationEnabled(boolean enabled);
        void onCreateSuccess();
        void onCreateFailed(String message);
    }
}
