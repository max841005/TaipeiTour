package com.cst.taipeitour.data.network.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseData(
    val `data`: List<Data>
) {

    @Parcelize
    data class Data(
        val images: List<Image>,
        val introduction: String,
        val name: String,
        val url: String
    ) : Parcelable {

        @Parcelize
        data class Image(
            val src: String
        ) : Parcelable
    }
}