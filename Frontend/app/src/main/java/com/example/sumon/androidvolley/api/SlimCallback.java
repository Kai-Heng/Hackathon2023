package com.example.sumon.androidvolley.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * @author kaiheng
 */
public class SlimCallback<T> implements Callback<T> {

    LambaInterface<T> lambdaInterface;

    String logTag;



    public SlimCallback(LambaInterface<T> lambdaInterface){
        this.lambdaInterface = lambdaInterface;
    }

    public SlimCallback(LambaInterface<T> lambdaInterface, String customTag){
        this.lambdaInterface = lambdaInterface;
        this.logTag = customTag;
    }



    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.isSuccessful()){
            lambdaInterface.doSomething(response.body());
        }
        else{
            Log.d(logTag, "Code:  "+response.code() + "    Msg:  "+response.message());
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        Log.d(logTag, "Thrown:  "+t.getMessage());

    }
}
