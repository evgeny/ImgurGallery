package com.ezino.imgurgallery.data

import com.ezino.imgurgallery.model.Gallery
import com.ezino.imgurgallery.model.Section
import io.reactivex.Maybe


interface ImgurRepository {
    /**
     * Return source instance of the available gallery list
     *
     * @param[section] one of tree available options: hot, top, user
     * @param[showViral] include in result the viral items
     */
    fun getGalleries(section: Section, showViral: Boolean): Maybe<List<Gallery>>
}