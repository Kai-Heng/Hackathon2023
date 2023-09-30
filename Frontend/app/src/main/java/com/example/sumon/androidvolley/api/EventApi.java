package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
/**
 * @author kaiheng
 */
public interface EventApi {
    @GET("event")
    Call<List<Event>> GetAllEvent();

    @POST("users/{id}")
    Call<Event> PostEventByPath(@Path("q") String question, @Path("a") String answer);

    @POST("event")
    Call<Event> PostEventByBody(@Body Event newEvent);
}
