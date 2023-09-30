package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author kaiheng
 */
public interface MessageApi {
    @GET("/message")
    Call<List<Message>> GetAllMessages();

//    @POST("/group/{gId}/groupChat/message")
//    Call<Message> PostMessageByBody(@Path("gId") int groupId, @Body Message newChat);
    @POST("/message")
    Call<Message> PostMessageByBody(@Body Message newChat);
}
