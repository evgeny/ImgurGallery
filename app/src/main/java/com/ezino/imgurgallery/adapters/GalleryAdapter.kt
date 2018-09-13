package com.ezino.imgurgallery.adapters

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ezino.imgurgallery.R
import com.ezino.imgurgallery.model.Image

class GalleryAdapter(diffCallback: DiffUtil.ItemCallback<Image>) : ListAdapter<Image, GalleryAdapter.ViewHolder>(diffCallback) {

    companion object {
        var counter: Int = 0
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.grid_item_image, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val item = getItem(i)

        counter++
        Log.d("GalleryAdapter", "counter=$counter")
        viewHolder.descView.text = item.description
        //https://api.imgur.com/3/image/{id}
        Glide.with(viewHolder.imageView.context)
                .load(item.link)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.imageView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var descView: TextView = itemView.findViewById(R.id.image_desc)
        var imageView: ImageView = itemView.findViewById(R.id.image)

    }
}