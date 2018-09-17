package com.ezino.imgurgallery.usecases

import com.ezino.imgurgallery.data.ImgurRepository
import com.ezino.imgurgallery.model.Image
import com.ezino.imgurgallery.model.Section
import io.reactivex.Observable

class GalleryInteractor(private val repo: ImgurRepository) {

    /**
     * retrieve all images from the gallery feed.
     */
    fun imageStream(section: Section, showViral: Boolean): Observable<Image> {
        return repo.getGalleries(section, showViral)
                .flatMapObservable { galleries -> Observable.fromIterable(galleries) }
                .filter { it.images != null }
                .flatMap { gallery -> Observable.fromIterable(gallery.images) }
    }
}