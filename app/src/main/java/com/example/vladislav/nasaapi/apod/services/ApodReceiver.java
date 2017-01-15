package com.example.vladislav.nasaapi.apod.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.vladislav.nasaapi.apod.services.ApodIntentServiceResult;

public class ApodReceiver extends BroadcastReceiver {

    ApodIntentServiceResult result;

    public ApodReceiver(ApodIntentServiceResult result) {
        this.result = result;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        result.onReceive(context, intent);
    }
}
