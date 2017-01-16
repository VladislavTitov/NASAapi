package com.example.vladislav.nasaapi.mars;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vladislav.nasaapi.R;

/**
 * Created by Vladislav on 16.01.2017.
 */

public class PhotosShowFragment extends Fragment {

    RecyclerView photos;

    String rover;
    String date;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            if (getArguments().getString("rover") != null){
                this.rover = getArguments().getString("rover");
            }
            if (getArguments().getString("date") != null){
                this.date = getArguments().getString("date");
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.photos_show_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photos = (RecyclerView) view.findViewById(R.id.photos);
        PhotosAdapter adapter = new PhotosAdapter();
        //TODO add setter in adapter

        photos.setAdapter(adapter);
        photos.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.photo_item_layout, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    public class PhotoHolder extends RecyclerView.ViewHolder{

        ImageView photo;

        public PhotoHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        public ImageView getPhoto() {
            return photo;
        }
    }
}
