package com.example.vladislav.nasaapi.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import com.example.vladislav.nasaapi.MainActivity;
import com.example.vladislav.nasaapi.R;

/**
 * Created by Elvira on 16.01.2017.
 */
public class Music {
    private static Music ourInstance = new Music();
    MediaPlayer mp;
    SharedPreferences sharedPreferences;

    public void start(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mp = MediaPlayer.create(context, R.raw.ktr);
        mp.setLooping(true);
        mp.setVolume(100, 100);
        mp.start();
        if (!sharedPreferences.getBoolean("sp_volume", false)) {
            mp.pause();
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if (s.equals("sp_volume")) {
                    if (!sharedPreferences.getBoolean("sp_volume", false)) {
                        mp.pause();
                    } else {
                        mp.start();
                    }
                }
            }
        });
    }
    public void pause() {
        mp.pause();
    }

    public void play() {
        mp.start();
    }

    public void stop() {
        mp.stop();
        mp.release();
    }
    private Music() {
    }
    public static Music getInstance() {
        return ourInstance;
    }
}
