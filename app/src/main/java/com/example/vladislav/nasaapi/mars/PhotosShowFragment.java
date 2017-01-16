package com.example.vladislav.nasaapi.mars;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vladislav.nasaapi.R;
import com.example.vladislav.nasaapi.mars.pojo.Photo;
import com.example.vladislav.nasaapi.mars.pojo.PhotosKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 16.01.2017.
 */

public class PhotosShowFragment extends Fragment implements PhotosShowCallback{

    RecyclerView photos;
    PhotosAdapter adapter;

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
        adapter = new PhotosAdapter();
        if (savedInstanceState == null) {
            getPhotosGetFragment();
        }else {
            getPhotosGetFragment().startTask();
        }
        photos.setAdapter(adapter);
        photos.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void putPhotos(PhotosKeeper keeper) {
        adapter.setKeeper(keeper);
    }

    private PhotosGetFragment getPhotosGetFragment(){
        FragmentManager manager = getFragmentManager();
        PhotosGetFragment fragment = (PhotosGetFragment) manager.findFragmentByTag(PhotosGetFragment.class.getName());

        if (fragment == null){
            fragment = new PhotosGetFragment();
            Bundle bundle = new Bundle();
            bundle.putString("rover", rover);
            bundle.putString("date", date);
            fragment.setArguments(bundle);

            manager.beginTransaction()
                    .add(fragment, fragment.getClass().getName())
                    .commit();
        }

        return fragment;
    }

    public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        PhotosKeeper keeper;
        List<Photo> list;

        public PhotosAdapter() {
            list = new ArrayList<>();
        }

        public void setKeeper(PhotosKeeper keeper) {
            if (keeper != null) {
                this.keeper = keeper;
                list = keeper.getPhotos();
            }else {
                list = new ArrayList<>();
            }
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.photo_item_layout, parent, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof PhotoHolder){
                Glide
                        .with(getActivity())
                        .load(list.get(position).getImgSrc())
                        .into(((PhotoHolder) holder).getPhoto());
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
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
