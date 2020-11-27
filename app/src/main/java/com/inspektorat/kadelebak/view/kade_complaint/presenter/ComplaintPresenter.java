package com.inspektorat.kadelebak.view.kade_complaint.presenter;

import android.content.Context;
import android.widget.Toast;

import com.inspektorat.kadelebak.model.ListUser;
import com.inspektorat.kadelebak.model.SuccessMessage;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.Util;
import com.inspektorat.kadelebak.view.kade_complaint.ComplaintService;
import com.inspektorat.kadelebak.view.kade_complaint.model.ComplaintCreateModel;
import com.inspektorat.kadelebak.view.kade_complaint.model.SectionModel;
import com.inspektorat.kadelebak.view.kade_complaint.model.create_page.ComplaintReplyModel;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.ComplaintModel;
import com.inspektorat.kadelebak.view.kade_complaint.view.ComplaintView;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumReplyModel;
import com.inspektorat.kadelebak.view.kade_forum.presenter.ForumPresenter;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintPresenter {

    private ComplaintView.View viewFitur;
    private ComplaintView.CreateComplaint viewCreate;
    private ComplaintView.ContentComplaint viewContent;
    private Context context;

    private int employeeId;
    private int roleId;
    private String sectionId;

    public ComplaintPresenter(ComplaintView.View viewFitur){
        this.viewFitur = viewFitur;
    }

    public ComplaintPresenter(ComplaintView.View viewFitur, int employeeId, int roleId, String sectionId) {
        this.viewFitur = viewFitur;
        this.employeeId = employeeId;
        this.roleId = roleId;
        this.sectionId = sectionId;
    }

    public ComplaintPresenter(ComplaintView.CreateComplaint viewCreate){
        this.viewCreate = viewCreate;
    }

    public ComplaintPresenter(ComplaintView.ContentComplaint viewContent, Context context) {
        this.viewContent = viewContent;
        this.context = context;
    }

    public void replyForum(int complaintId, String content, String employeeId) {
        if (content.length() == 0) {
            viewContent.setErrorValidationMessage("Tidak Boleh Kosong");
            viewContent.setErrorValidationEnabled(true);
        } else {
            viewContent.setErrorValidationEnabled(false);

            ComplaintReplyModel model = new ComplaintReplyModel(complaintId, content, Integer.valueOf(employeeId), Util.getDateTimeNow());
            sendToReply(model);
        }

    }

    private void sendToReply(ComplaintReplyModel model) {
        Call<SuccessMessage> call = this.initService().sendReplyComplaint(model);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                if (response.isSuccessful()){
                    viewContent.notifyForum();
                    viewContent.onReplySuccess(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SuccessMessage> call, Throwable t) {

            }
        });
    }

    public void getComplaintData(){
        Call<List<ComplaintModel>> call = this.initService().getAllComplaint();
        call.enqueue(new Callback<List<ComplaintModel>>() {
            @Override
            public void onResponse(Call<List<ComplaintModel>> call, Response<List<ComplaintModel>> response) {
                List<ComplaintModel> list = response.body();
                hideLoading();
                if (list.size() > 0){
                    viewFitur.inflateData(true);
                } else {
                    viewFitur.inflateData(false);
                }

                if (sectionId.length() == 0) {
                    viewFitur.showDataRoleUser(list);
                    viewFitur.hideIconCreate(true);
                } else {
                    viewFitur.showDataRoleOperator(list);
                    viewFitur.hideIconCreate(false);
                }
            }

            @Override
            public void onFailure(Call<List<ComplaintModel>> call, Throwable t) {

            }
        });

        showLoading();
    }

    public void getComplaintById(int complaintId) {
        Call<ComplaintModel> call = this.initService().getComplaintById(complaintId);
        call.enqueue(new Callback<ComplaintModel>() {
            @Override
            public void onResponse(Call<ComplaintModel> call, Response<ComplaintModel> response) {
                viewContent.showDataComplaint(response.body());
            }

            @Override
            public void onFailure(Call<ComplaintModel> call, Throwable t) {

            }
        });
    }

    public void getSection() {
        Call<List<SectionModel>> call = this.initService().getAllSection();
        call.enqueue(new Callback<List<SectionModel>>() {
            @Override
            public void onResponse(Call<List<SectionModel>> call, Response<List<SectionModel>> response) {
                viewCreate.renderSpinner(response.body());
            }

            @Override
            public void onFailure(Call<List<SectionModel>> call, Throwable t) {

            }
        });
    }

    public void creteComplaint(int employeeId, String sectionId, String content, boolean anonymous, boolean notify) {
        if (viewCreate.validateInput()) {
            viewCreate.setErrorValidationEnabled(false);
            ComplaintCreateModel model = new ComplaintCreateModel(content, employeeId, Integer.valueOf(sectionId), anonymous, notify, Util.getDateTimeNow());
            saveComplaint(model);
            viewCreate.showLoading();
        }
    }

    private void saveComplaint(ComplaintCreateModel createModel){
        Call<SuccessMessage> call = initService().sendReply(createModel);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                String message = response.body().getMessage();
                if (response.isSuccessful()) {
                    viewCreate.hideLoading();
                    viewCreate.onCreateSuccess(message);
                } else {
                    viewCreate.hideLoading();
                    viewCreate.onCreateFailed(message);
                }
            }

            @Override
            public void onFailure(Call<SuccessMessage> call, Throwable t) {

            }
        });
    }

    public void deleteComplaintById(int id) {
        Call<SuccessMessage> call = this.initService().deleteComplaintById(id);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                viewFitur.onDeleteSuccess();
            }

            @Override
            public void onFailure(Call<SuccessMessage> call, Throwable t) {
                viewFitur.onDeleteFailed();
            }
        });
    }

    public void showLoading(){
        viewFitur.showLoading();
    }

    public void hideLoading(){
        viewFitur.hideLoading();
    }

    private ComplaintService initService(){
        ComplaintService service = NetworkClient.getRetrofit()
                .create(ComplaintService.class);
        return service;
    }
}
