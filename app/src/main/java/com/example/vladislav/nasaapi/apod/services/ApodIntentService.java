package com.example.vladislav.nasaapi.apod.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.vladislav.nasaapi.apod.ApodFragment;
import com.example.vladislav.nasaapi.apod.ApodService;
import com.example.vladislav.nasaapi.apod.pojo.ApodPojo;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApodIntentService extends IntentService {

    public ApodIntentService() {
        super("ApodIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(ApodFragment.TAG, "this is onHandleIntent");

        ApodPojo apodPojo = null;

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.nasa.gov")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApodService service = retrofit.create(ApodService.class);

            Call<ApodPojo> apodPojoCall = service.apod(ApodService.appid);


            Response<ApodPojo> response = apodPojoCall.execute();

            apodPojo = response.body();

        }catch (IOException e){
            e.printStackTrace();
        }



        Intent intent1 = new Intent();
        intent1.putExtra("date", apodPojo.getDate());
        intent1.putExtra("photo", apodPojo.getUrl());
        intent1.putExtra("title", apodPojo.getTitle());
        intent1.putExtra("copyright", apodPojo.getCopyright());
        intent1.putExtra("explanation", apodPojo.getExplanation());
        intent1.setAction(ApodFragment.ACTION_NAME_FOR_APOD_SERVICE);
        sendBroadcast(intent1);

    }
}