package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Group;
import com.example.sumon.androidvolley.model.GroupChat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GroupChatApi {
    @GET("groupChat")
    Call<List<GroupChat>> GetAllGroupChats();

    @POST("groupChat")
    Call<Group> PostGroupChat(@Body GroupChat newGroupChat);

    @GET("groupChat/message")
    Call<List<GroupChat>> GetGroupMessages();


}
