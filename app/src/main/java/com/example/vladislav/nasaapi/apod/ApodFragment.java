package com.example.vladislav.nasaapi.apod;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.vladislav.nasaapi.R;
import com.example.vladislav.nasaapi.apod.services.ApodIntentService;
import com.example.vladislav.nasaapi.apod.services.ApodIntentServiceResult;
import com.example.vladislav.nasaapi.apod.services.ApodReceiver;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ApodFragment extends Fragment implements ApodIntentServiceResult{
    TextView date;
    CircleImageView photo;
    TextView copyright;
    TextView title;
    TextView explanation;

    WebView videoView;

    ApodReceiver apodReceiver;

    public static final String TAG = "mylogs";

    public static final String ACTION_NAME_FOR_APOD_SERVICE = "com.example.vladislav.nasaapi.apod.service.ApodIntentService";

    public ApodFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.apod_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = (TextView) view.findViewById(R.id.date);
        photo = (CircleImageView) view.findViewById(R.id.photo);
        copyright = (TextView) view.findViewById(R.id.copyright);
        title = (TextView) view.findViewById(R.id.title);
        explanation = (TextView) view.findViewById(R.id.explanation);

        videoView = (WebView) view.findViewById(R.id.video);

        Intent intentService = new Intent(getActivity(), ApodIntentService.class);
        getActivity().startService(intentService);

        Log.d(ApodFragment.TAG, "onViewCreated after startService");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "this is onResume");
        apodReceiver = new ApodReceiver(this);
        IntentFilter filter = new IntentFilter(ACTION_NAME_FOR_APOD_SERVICE);
        getActivity().registerReceiver(apodReceiver, filter);

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(apodReceiver);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "this is frag onReceive");
        if (intent.getExtras()!= null){
            date.setText(intent.getStringExtra("date"));
            copyright.setText(intent.getStringExtra("copyright"));
            title.setText(intent.getStringExtra("title"));
            explanation.setText(intent.getStringExtra("explanation"));

            /*Picasso.with(getActivity())
                    .load(intent.getStringExtra("photo"))
                    .error( R.drawable.space)
                    .into(photo);*/
            if (intent.getStringExtra("mediatype") != null && intent.getStringExtra("mediatype").equals("video")){

                photo.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);

                /*videoView.setVideoURI(Uri.parse(intent.getStringExtra("photo")));

                videoView.setMediaController(new MediaController(getActivity()));
                videoView.requestFocus(0);*/

                videoView.getSettings().setJavaScriptEnabled(true);
                videoView.getSettings().setPluginState(WebSettings.PluginState.ON);
                videoView.loadUrl(intent.getStringExtra("photo"));
                videoView.setWebChromeClient(new WebChromeClient());

            }else {
                Glide
                        .with(getActivity())
                        .load(intent.getStringExtra("photo"))
                        .error(R.drawable.space)
                        .into(photo);
            }
        }

    }
}