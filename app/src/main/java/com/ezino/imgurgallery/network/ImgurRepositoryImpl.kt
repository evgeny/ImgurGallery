package com.ezino.imgurgallery.network

import com.ezino.imgurgallery.ImgurRepository
import com.ezino.imgurgallery.model.Gallery
import io.reactivex.Observable

class ImgurRepositoryImpl : ImgurRepository {
    private val imgurApiService by lazy { ImgurApiService.newInstance() }

    override fun getGalleries(section: String, showViral: Boolean): Observable<List<Gallery>> {
        return imgurApiService.getGalleries(section, showViral).map { response -> response.data }
    }
}