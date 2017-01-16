package com.example.vladislav.nasaapi;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.preference.PreferenceActivity;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.vladislav.nasaapi.apod.ApodFragment;
import com.example.vladislav.nasaapi.settings.Music;
import com.example.vladislav.nasaapi.settings.SettingsFragment;
import com.example.vladislav.nasaapi.mars.RoversFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ChangeFragmentListener{

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    private String title = "Daily photo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Music.getInstance().start(this);

        if (savedInstanceState == null){
            drawer.openDrawer(GravityCompat.START);
        }else {
            setTitle(savedInstanceState.getString("title", "ProjectStars"));
        }

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = null;

        switch (id){
            case R.id.nav_daily:
                fragmentClass = ApodFragment.class;
                Snackbar.make(drawer, "It's daily photo!", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.nav_mars:
                fragmentClass = RoversFragment.class;
                Snackbar.make(drawer, "It's mars rovers photos!", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsFragment.class;
                Snackbar.make(drawer, "It's settings!", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.nav_exit:
                finish();
                return false;
        }

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        replaceFragment(fragment, item.getTitle().toString());
        item.isChecked();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment, String title){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.apod_container, fragment, fragment.getClass().getName())
                .commit();

        setTitle(title);
        this.title = title;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("title", title);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void changeFragment(Class fragmentClass, Bundle arguments) {
        Fragment fragment = null;

        try{
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        fragment.setArguments(arguments);

        replaceFragment(fragment, this.title);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Music.getInstance().stop();
    }
}

