package com.ezino.imgurgallery

import com.ezino.imgurgallery.model.Image
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class GalleryInteractor(private val repo: ImgurRepository) {

    /**
     * retrieve all images from the gallery feed.
     */
    fun imageStream(section: String, showViral: Boolean): Observable<Image> {
        return repo.getGalleries(section, showViral)
                .flatMapObservable { galleries -> Observable.fromIterable(galleries) }
                .filter { it.images != null }
                .flatMap { gallery -> Observable.fromIterable(gallery.images) }
    }

    fun imageList(section: String, showViral: Boolean): Flowable<List<Image>> {
        return repo.getGalleries(section, showViral)
                .flatMapObservable { galleries -> Observable.fromIterable(galleries) }
                .filter { it.images != null }
                .flatMap { gallery -> Observable.fromIterable(gallery.images) }
                .toList().toFlowable().subscribeOn(Schedulers.io())
    }
}