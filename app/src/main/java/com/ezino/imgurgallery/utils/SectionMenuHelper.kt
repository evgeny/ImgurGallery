package com.ezino.imgurgallery.utils

import com.ezino.imgurgallery.R
import com.ezino.imgurgallery.model.Section

fun mapToItemId(section: Section): Int {
    return when (section) {
        Section.HOT -> R.id.hot
        Section.TOP -> R.id.top
        Section.USER -> R.id.user
    }
}