package com.ezino.imgurgallery.network

import com.ezino.imgurgallery.ImgurRepository
import com.ezino.imgurgallery.model.Gallery
import io.reactivex.Observable

class ImgurRepositoryImpl : ImgurRepository {
    private val imgurApiService by lazy { ImgurApiService.newInstance() }

    override fun getGalleries(): Observable<List<Gallery>> {
        return imgurApiService.getGalleries().map { response -> response.data  }
    }
}