package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Group;
import com.example.sumon.androidvolley.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GroupApi {
    @GET("group")
    Call<List<Group>> GetAllGroups();

    @GET("users/{id}/groups")
    Call<List<Group>> GetUserGroup(@Path("id") int userId);

    @GET("group/{groupId}/message")
    Call<List<Message>> GetAllMessage(@Path("groupId") int groupId);

    @POST("group")
    Call<Group> PostCreateGroup(@Body Group newGroup);

    @POST("group/{groupId}/message")
    Call<Message> PostAMessage(@Path("groupId") int groupId, @Body Message message);

    @POST("group/addUser")
    Call<Group> PostNewUsers(@Body Group newUsers);

}
