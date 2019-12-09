package com.inspektorat.kadelebak.view.kade_complaint;

import com.inspektorat.kadelebak.model.SuccessMessage;
import com.inspektorat.kadelebak.view.kade_complaint.model.ComplaintCreateModel;
import com.inspektorat.kadelebak.view.kade_complaint.model.SectionModel;
import com.inspektorat.kadelebak.view.kade_complaint.model.create_page.ComplaintReplyModel;
import com.inspektorat.kadelebak.view.kade_complaint.model.list_page.ComplaintModel;
import com.inspektorat.kadelebak.view.kade_forum.model.ForumReplyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ComplaintService {

    @GET("section")
    Call<List<SectionModel>> getAllSection();

    @GET("complaint")
    Call<List<ComplaintModel>> getAllComplaint();

    @GET("complaint/{id}")
    Call<ComplaintModel> getComplaintById(@Path("id") int id);

    @POST("complaint")
    Call<SuccessMessage> sendReply(@Body ComplaintCreateModel complaintCreateModel);

    @POST("complaint/reply")
    Call<SuccessMessage> sendReplyComplaint(@Body ComplaintReplyModel complaintReplyModel);
}
