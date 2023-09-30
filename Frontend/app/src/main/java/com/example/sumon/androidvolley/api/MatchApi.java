package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
/**
 * @author kaiheng
 */
public interface MatchApi {
    @GET("users/{id}/friends")
    Call<List<Match>> GetAllMatches();

    @GET("users/{id}/friends")
    Call<List<Match>> GetNewMatch();

    @POST("users/{uid}/hobby/{hid}")
    Call<Match> PostHobbytoUser(@Path("uId") int userId, @Path("hId") int hobbyId);

}
