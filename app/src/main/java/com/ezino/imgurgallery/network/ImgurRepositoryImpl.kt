package com.ezino.imgurgallery.network

import com.ezino.imgurgallery.data.ImgurRepository
import com.ezino.imgurgallery.model.Gallery
import com.ezino.imgurgallery.model.Section
import io.reactivex.Maybe

class ImgurRepositoryImpl : ImgurRepository {
    private val imgurApiService by lazy { ImgurApiService.newInstance() }

    override fun getGalleries(section: Section, showViral: Boolean): Maybe<List<Gallery>> {
        return imgurApiService.getGalleries(mapTo(section), showViral).map { response -> response.data }
    }

    private fun mapTo(section: Section): String {
        return when (section) {
            Section.HOT -> "hot"
            Section.TOP -> "top"
            Section.USER -> "user"
        }
    }
}