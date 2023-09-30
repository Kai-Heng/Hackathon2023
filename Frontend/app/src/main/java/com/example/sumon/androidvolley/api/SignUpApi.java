package com.example.sumon.androidvolley.api;

import com.example.sumon.androidvolley.model.SignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpApi {
    @POST("users")
    Call<SignUp> PostNewUser(@Body SignUp newUser);
}
