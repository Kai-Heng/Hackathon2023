package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Post;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * @author kaiheng
 */
public interface PostApi{

    @GET("users")
    Call<Post> getFirstPost();
}
