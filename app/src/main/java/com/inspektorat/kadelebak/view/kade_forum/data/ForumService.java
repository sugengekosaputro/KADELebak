package com.inspektorat.kadelebak.view.kade_forum.data;

import com.inspektorat.kadelebak.model.SuccessMessage;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumCreateModel;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumModel;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumReplyModel;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ForumService {

    @GET("forum")
    Call<List<ForumModel>> getAllForum();

    @GET("forum/{id}")
    Call<ForumModel> getForumById(@Path("id") int id);

    @POST("forum")
    Call<SuccessMessage> createForum(@Body ForumCreateModel forumCreateModel);

    @POST("forum/reply")
    Call<SuccessMessage> sendReply(@Body ForumReplyModel forumReplyModel);
}
