package com.example.vladislav.nasaapi.mars;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.vladislav.nasaapi.apod.ApodFragment;
import com.example.vladislav.nasaapi.mars.pojo.PhotosKeeper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosGetFragment extends Fragment {

    private PhotosShowCallback callback;
    private PhotosGetAsyncTask asyncTask;
    private String rover;
    private String date;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachCallback();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attachCallback();
    }

    private void attachCallback(){
        callback = (PhotosShowCallback) getFragmentManager().findFragmentByTag(PhotosShowFragment.class.getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (getArguments() != null){
            if (getArguments().getString("rover") != null){
                this.rover = getArguments().getString("rover");
            }
            if (getArguments().getString("date") != null){
                this.date = getArguments().getString("date");
            }
        }
        startTask();
    }

    public void startTask(){
            asyncTask = new PhotosGetAsyncTask();
            asyncTask.execute(rover, date);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    public class PhotosGetAsyncTask extends AsyncTask<String, Void, PhotosKeeper>{

        @Override
        protected PhotosKeeper doInBackground(String... params) {
            String rover = params[0];
            String date = params[1];

            PhotosKeeper keeper = null;

            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.nasa.gov")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                MarsService marsService = retrofit.create(MarsService.class);

                Call<PhotosKeeper> photosKeeperCall = null;
                if (rover.equals("curiosity")){
                    photosKeeperCall = marsService.curiosityQuery(date, MarsService.appid);
                }else if (rover.equals("opportunity")){
                    photosKeeperCall = marsService.opportunityQuery(date, MarsService.appid);
                }else if (rover.equals("spirit")){
                    photosKeeperCall = marsService.spiritQuery(date, MarsService.appid);
                }

                Response<PhotosKeeper> response = photosKeeperCall.execute();

                keeper = response.body();


            }catch (IOException ex){
                ex.printStackTrace();
            }

            return keeper;
        }

        @Override
        protected void onPostExecute(PhotosKeeper keeper) {
            super.onPostExecute(keeper);
            callback.putPhotos(keeper);
        }
    }

}
