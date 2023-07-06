package com.cst.taipeitour.ui.main.content

import androidx.lifecycle.ViewModel
import com.cst.taipeitour.data.entities.ContentEntity
import com.cst.taipeitour.data.network.response.ResponseData

class ContentViewModel : ViewModel() {

    val entity = ContentEntity()

    fun setData(
        data: ResponseData.Data
    ) {

        with(entity) {
            title.value = data.name
            content.value = data.introduction
            url.value = data.url
        }
    }
}