package com.ezino.imgurgallery.viewmodles

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.ezino.imgurgallery.GalleryInteractor
import com.ezino.imgurgallery.model.Image
import com.ezino.imgurgallery.network.ImgurRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageListViewModel : ViewModel() {
    // todo replace by di
    private val galleryInteractor = GalleryInteractor(ImgurRepositoryImpl())

    private val disposables = CompositeDisposable()

    private val imageList = MutableLiveData<List<Image>>()


    /*fun getImages(): LiveData<List<Image>> {
        return LiveDataReactiveStreams
                .fromPublisher(galleryInteractor.imageList("hot", true))
    }*/

    fun getImages(): LiveData<List<Image>> {
        if (imageList.value == null) {
            loadImages()
        }

        return imageList
    }

    private fun loadImages() {
        val disposable = galleryInteractor.imageStream("hot", true)
                .toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list -> imageList.setValue(list) },
                        { error -> Log.e("safdsf", "todo show error", error) }
                )

        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
    }
}