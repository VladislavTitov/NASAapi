
package com.example.vladislav.nasaapi.mars.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhotosKeeper {

    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
