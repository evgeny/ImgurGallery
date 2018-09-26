package com.ezino.imgurgallery.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ezino.imgurgallery.model.Image

class ImageDiffCallback: DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id.equals(newItem.id, true)
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id.equals(newItem.id, true)
    }
}