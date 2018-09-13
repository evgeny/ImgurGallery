package com.ezino.imgurgallery.network

import com.ezino.imgurgallery.model.ImgurResponse
import io.reactivex.Maybe
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImgurApiService {

    @GET("/3/gallery/{section}/0.json")
    fun getGalleries(@Path("section") section: String, @Query("showViral") showViral: Boolean): Maybe<ImgurResponse>

    companion object Factory {
        fun newInstance(): ImgurApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.imgur.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(ImgurApiService::class.java)
        }
    }
}