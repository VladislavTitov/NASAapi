package com.example.vladislav.nasaapi.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.vladislav.nasaapi.R;

/**
 * Created by Elvira on 15.01.2017.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
