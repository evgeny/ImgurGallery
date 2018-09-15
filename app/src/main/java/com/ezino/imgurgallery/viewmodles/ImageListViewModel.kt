package com.ezino.imgurgallery.viewmodles

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.ezino.imgurgallery.GalleryInteractor
import com.ezino.imgurgallery.model.Image
import com.ezino.imgurgallery.network.ImgurRepositoryImpl

class ImageListViewModel: ViewModel() {
    // todo replace by di
    val galleryInteractor = GalleryInteractor(ImgurRepositoryImpl())

    fun getImages(): LiveData<List<Image>> {
        galleryInteractor.imageStream("hot", true)
        return LiveDataReactiveStreams.fromPublisher(galleryInteractor.imageList("hot", true))
    }
}