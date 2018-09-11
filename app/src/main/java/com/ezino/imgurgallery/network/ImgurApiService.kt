package com.ezino.imgurgallery.network

import com.ezino.imgurgallery.model.ImgurResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ImgurApiService {

    @GET("/3/gallery/hot/viral/0.json")
    fun getGalleries(): Observable<ImgurResponse>

    companion object Factory {
        fun newInstance(): ImgurApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.imgur.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(ImgurApiService::class.java)
        }
    }
}