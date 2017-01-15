package com.example.vladislav.nasaapi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

public class Splash extends AppCompatActivity {
    ProgressBar mProgressBar;
    final int max = 100;
    int cnt;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        h = new Handler();
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mProgressBar.setMax(max);
        mProgressBar.setProgress(0);
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    for (cnt = 1; cnt < max; cnt = cnt + 2) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        // обновляем ProgressBar
                        h.post(updateProgress);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 5 * 1000);
    }

    // обновление ProgressBar
    Runnable updateProgress = new Runnable() {
        public void run() {
            mProgressBar.setProgress(cnt);
        }
    };
}
