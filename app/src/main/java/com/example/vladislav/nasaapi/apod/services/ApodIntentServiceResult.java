package com.example.vladislav.nasaapi.apod.services;

import android.content.Context;
import android.content.Intent;

public interface ApodIntentServiceResult {
    void onReceive(Context context, Intent intent);
}
