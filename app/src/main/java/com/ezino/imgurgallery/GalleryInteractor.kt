package com.ezino.imgurgallery

import com.ezino.imgurgallery.model.Image
import io.reactivex.Observable

class GalleryInteractor(private val repo: ImgurRepository) {

    fun getImageLink(): Observable<Image> {
        return repo.getGalleries()
                .flatMap { galleries -> Observable.fromIterable(galleries) }
                .flatMap { gallery -> Observable.fromIterable(gallery.images) }
    }
}