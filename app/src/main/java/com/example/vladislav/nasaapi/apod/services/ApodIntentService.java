package com.example.vladislav.nasaapi.apod.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.vladislav.nasaapi.apod.ApodFragment;
import com.example.vladislav.nasaapi.apod.ApodService;
import com.example.vladislav.nasaapi.apod.pojo.ApodPojo;

import java.io.IOException;
import java.text.SimpleDateFormat;

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

            if (response.isSuccessful()) {
                apodPojo = response.body();
            }else {
                apodPojo = new ApodPojo();

                apodPojo.setDate("");

                apodPojo.setUrl("");
                apodPojo.setMediaType("audio");
                apodPojo.setTitle("Error!");
                apodPojo.setExplanation("Perhaps you are disconnected Internet!");
                apodPojo.setCopyright("");
            }

        }catch (IOException e){
            e.printStackTrace();
        }



        Intent intent1 = new Intent();
        intent1.putExtra("date", apodPojo != null ? apodPojo.getDate() : "");
        intent1.putExtra("mediatype", apodPojo != null ? apodPojo.getMediaType() : "");
        intent1.putExtra("photo", apodPojo != null ? apodPojo.getUrl() : "");
        intent1.putExtra("title", apodPojo != null ? apodPojo.getTitle() : "Error!");
        intent1.putExtra("copyright", apodPojo != null ? apodPojo.getCopyright() : "");
        intent1.putExtra("explanation", apodPojo != null ? apodPojo.getExplanation() : "Perhaps you are disconnected Internet!");
        intent1.setAction(ApodFragment.ACTION_NAME_FOR_APOD_SERVICE);
        sendBroadcast(intent1);

    }
}