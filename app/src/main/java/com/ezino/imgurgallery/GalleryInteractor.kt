package com.ezino.imgurgallery

import com.ezino.imgurgallery.model.Image
import io.reactivex.Observable

class GalleryInteractor(private val repo: ImgurRepository) {

    /**
     * retrieve all images from the gallery feed.
     */
    fun imageStream(section: String, showViral: Boolean): Observable<Image> {
        return repo.getGalleries(section, showViral)
                .flatMap { galleries -> Observable.fromIterable(galleries) }
                .filter { it.images != null }
                .flatMap { gallery -> Observable.fromIterable(gallery.images) }
    }
}