package com.example.vladislav.nasaapi.apod;


import com.example.vladislav.nasaapi.apod.pojo.ApodPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApodService {

    String appid = "Vrp2ETElvhybzM1RrLIDXPHqETfmBoW4ZTPsMbqg";

    @GET("/planetary/apod")
    Call<ApodPojo> apod(@Query("api_key") String appid);
}