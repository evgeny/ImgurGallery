package com.ezino.imgurgallery.adapters

import android.support.v7.util.DiffUtil
import com.ezino.imgurgallery.model.Image

class ImageDiffCallback: DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id.equals(newItem.id, true)
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id.equals(newItem.id, true)
    }
}