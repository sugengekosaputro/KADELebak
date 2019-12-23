package com.inspektorat.kadelebak.view.kade_complaint.view;

import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.view.kade_complaint.model.SectionModel;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.ComplaintModel;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumModel;

import java.util.List;

public class ComplaintView {

    public interface View {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void hideIconCreate(boolean isVisible);
        void showDataRoleUser(List<ComplaintModel> complaintModelList);
        void showDataRoleOperator(List<ComplaintModel> complaintModelList);
        void onDeleteSuccess();
        void onDeleteFailed();
    }

    public interface CreateComplaint {
        void showLoading();
        void hideLoading();
        void showError(String message);
        void setErrorValidationMessage(String message);
        void setErrorValidationEnabled(boolean enabled);
        void onCreateSuccess(String message);
        void onCreateFailed(String message);
        void renderSpinner(List<SectionModel> sectionModelList);
    }

    public interface ContentComplaint {
        void showError(String message);
        void setErrorValidationMessage(String message);
        void setErrorValidationEnabled(boolean enabled);
        void onReplySuccess(String message);
        void showDataComplaint(ComplaintModel complaintModel);
        void notifyForum();
    }


}
