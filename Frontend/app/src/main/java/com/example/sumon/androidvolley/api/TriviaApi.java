package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Trivia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
/**
 * @author kaiheng
 */
public interface TriviaApi {

    @GET("/users")
    Call<List<Trivia>> GetAllTrivia();

    @GET("/users/{id}")
    Call<Trivia> GetAUser(@Path("id") int id);

    @POST("/users/{id}")
    Call<Trivia> PostTriviaByPath(@Path("id") int id, @Body Trivia newTrivia);

    @POST("users")
    Call<Trivia> PostTriviaByBody(@Body Trivia newTrivia);

    @POST("users/{uId}/hobby/{hId}")
    Call<Trivia> PostHobbytoUser(@Path("uId") int userId, @Path("hId") int hobbyId);


}
