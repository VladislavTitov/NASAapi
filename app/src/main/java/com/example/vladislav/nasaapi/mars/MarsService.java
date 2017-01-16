package com.example.vladislav.nasaapi.mars;

import com.example.vladislav.nasaapi.mars.pojo.PhotosKeeper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vladislav on 16.01.2017.
 */

public interface MarsService {

    String appid = "Vrp2ETElvhybzM1RrLIDXPHqETfmBoW4ZTPsMbqg";

    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    Call<PhotosKeeper> curiosityQuery(@Query("earth_date") String date, @Query("api_key") String appid);

    @GET("/mars-photos/api/v1/rovers/opportunity/photos")
    Call<PhotosKeeper> opportunityQuery(@Query("earth_date") String date, @Query("api_key") String appid);

    @GET("/mars-photos/api/v1/rovers/spirit/photos")
    Call<PhotosKeeper> spiritQuery(@Query("earth_date") String date, @Query("api_key") String appid);
}
