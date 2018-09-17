package com.ezino.imgurgallery.adapters

import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ezino.imgurgallery.R
import com.ezino.imgurgallery.model.Image
import com.ezino.imgurgallery.utils.GlideApp

class GalleryAdapter(diffCallback: DiffUtil.ItemCallback<Image>, private val context: Context)
    : ListAdapter<Image, GalleryAdapter.ViewHolder>(diffCallback) {

    /**
     * number of columns in grid layout
     */
    private val gridColumns = 2

    /**
     * evaluate approximately the grid layout column width
     */
    private val gridLayoutColumnWidth = context.resources.displayMetrics.widthPixels / gridColumns

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.grid_item_image, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val item = getItem(i)

        viewHolder.descView.text = item.description
        GlideApp.with(context)
                .load(item.link)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(gridLayoutColumnWidth, context.resources.getDimension(R.dimen.card_height).toInt())
                .into(viewHolder.imageView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var descView: TextView = itemView.findViewById(R.id.image_desc)
        var imageView: ImageView = itemView.findViewById(R.id.image)
    }
}