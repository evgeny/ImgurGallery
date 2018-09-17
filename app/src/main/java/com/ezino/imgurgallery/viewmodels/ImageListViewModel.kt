package com.ezino.imgurgallery.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.ezino.imgurgallery.model.Image
import com.ezino.imgurgallery.model.Section
import com.ezino.imgurgallery.network.ImgurRepositoryImpl
import com.ezino.imgurgallery.usecases.GalleryInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageListViewModel : ViewModel() {
    // todo replace by di
    private val galleryInteractor = GalleryInteractor(ImgurRepositoryImpl())

    private val disposables = CompositeDisposable()

    private val imageList = MutableLiveData<List<Image>>()

    /**
     * selected section, on update reload the image field
     */
    var selectedSection: Section = Section.HOT
        set(value) {
            loadImages(value)
            field = value
        }

    fun getImages(): LiveData<List<Image>> {
        if (imageList.value == null) {
            loadImages(selectedSection)
        }

        return imageList
    }

    private fun loadImages(section: Section) {
        val disposable = galleryInteractor.imageStream(section, true)
                .toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { list -> imageList.setValue(list) },
                        { error -> Log.e("ImageListViewModel", "todo show error", error) }
                )

        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
    }
}