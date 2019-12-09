package com.inspektorat.kadelebak.view.kade_forum.presenter;

import android.content.Context;

import com.inspektorat.kadelebak.model.ListUser;
import com.inspektorat.kadelebak.model.SuccessMessage;
import com.inspektorat.kadelebak.model.User;
import com.inspektorat.kadelebak.networking.NetworkClient;
import com.inspektorat.kadelebak.view.kade_forum.data.ForumCache;
import com.inspektorat.kadelebak.view.kade_forum.data.ForumService;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumModel;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumReplyModel;
import com.inspektorat.kadelebak.view.kade_forum.view.ForumView;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForumPresenter {

    private ForumView.View view;
    private ForumView.ContentForum contentForumView;
    private Context context;


    public ForumPresenter(ForumView.View view) {
        this.view = view;
    }

    public ForumPresenter(ForumView.ContentForum contentForumView, Context context) {
        this.contentForumView = contentForumView;
        this.context = context;
    }

    public void initialize() {
        this.getRetrofit();
    }

    public void replyForum(int forumId, String content, String employeeId) {
        if (content.length() == 0) {
            contentForumView.setErrorValidationMessage("Tidak Boleh Kosong");
            contentForumView.setErrorValidationEnabled(true);
        } else {
            contentForumView.setErrorValidationEnabled(false);

            ForumReplyModel model = new ForumReplyModel(forumId, content, Integer.valueOf(employeeId));
            sendToReply(model);
        }

    }

    private void sendToReply(ForumReplyModel model) {
        Call<SuccessMessage> call = initService().sendReply(model);
        call.enqueue(new Callback<SuccessMessage>() {
            @Override
            public void onResponse(Call<SuccessMessage> call, Response<SuccessMessage> response) {
                if (response.isSuccessful()) {
                    contentForumView.notifyForum();
                    contentForumView.onReplySuccess(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SuccessMessage> call, Throwable t) {

            }
        });
    }

    private void getRetrofit() {
        Call<List<ForumModel>> call = initService().getAllForum();
        call.enqueue(new Callback<List<ForumModel>>() {
            @Override
            public void onResponse(Call<List<ForumModel>> call, Response<List<ForumModel>> response) {
                List<ForumModel> forumModelList = response.body();
                view.showDataForum(forumModelList);
            }

            @Override
            public void onFailure(Call<List<ForumModel>> call, Throwable t) {

            }
        });
    }

    public void getRetrofitById(int forumId) {
        Call<ForumModel> call = initService().getForumById(forumId);
        call.enqueue(new Callback<ForumModel>() {
            @Override
            public void onResponse(Call<ForumModel> call, Response<ForumModel> response) {
                contentForumView.showDataForum(response.body());
            }

            @Override
            public void onFailure(Call<ForumModel> call, Throwable t) {

            }
        });
    }

    private ForumService initService() {
        ForumService service = NetworkClient.getRetrofit()
                .create(ForumService.class);
        return  service;
    }
}
