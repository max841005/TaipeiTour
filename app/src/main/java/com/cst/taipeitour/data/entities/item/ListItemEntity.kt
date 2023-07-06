package com.cst.taipeitour.data.entities.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListItemEntity(
    val title: String = "",
    val content: String = ""
) : Parcelable