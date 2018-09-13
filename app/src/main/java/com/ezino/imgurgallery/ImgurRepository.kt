package com.ezino.imgurgallery

import com.ezino.imgurgallery.model.Gallery
import io.reactivex.Observable


interface ImgurRepository {
    fun getGalleries(section: String, showViral: Boolean): Observable<List<Gallery>>
}