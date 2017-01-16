package com.example.vladislav.nasaapi.mars;

import com.example.vladislav.nasaapi.mars.pojo.PhotosKeeper;

/**
 * Created by Vladislav on 16.01.2017.
 */

public interface PhotosShowCallback {

    void putPhotos(PhotosKeeper keeper);

}
