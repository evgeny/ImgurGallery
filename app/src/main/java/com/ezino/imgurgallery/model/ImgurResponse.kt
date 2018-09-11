package com.ezino.imgurgallery.model

data class ImgurResponse(val data: List<Gallery>, val success: Boolean, val status: Int)

data class Gallery(val id: String, val images: List<Image>?)

data class Image(val id: String, val description: String, val link: String)