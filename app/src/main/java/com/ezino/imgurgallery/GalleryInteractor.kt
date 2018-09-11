package com.ezino.imgurgallery

import io.reactivex.Observable

class GalleryInteractor(private val repo: ImgurRepository) {

    fun getImageLink(): Observable<String> {
        return repo.getGalleries()
                .flatMap { galleries -> Observable.fromIterable(galleries) }
                .flatMap { gallery -> Observable.fromIterable(gallery.images) }
                .map { image -> image.link }
    }
}