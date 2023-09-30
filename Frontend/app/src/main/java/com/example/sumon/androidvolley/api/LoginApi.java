package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginApi {
    @GET("users/login/{username}/{password}")
    Call<Login> GetLoginCheck(@Path("username") String username, @Path("password") String password);

}
