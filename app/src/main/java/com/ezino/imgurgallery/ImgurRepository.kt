package com.ezino.imgurgallery

import com.ezino.imgurgallery.model.Gallery
import io.reactivex.Maybe


interface ImgurRepository {
    /**
     * Return source instance of the available gallery list
     *
     * @param[section] one of tree available options: hot, top, user
     * @param[showViral] include in result the viral items
     */
    fun getGalleries(section: String, showViral: Boolean): Maybe<List<Gallery>>
}