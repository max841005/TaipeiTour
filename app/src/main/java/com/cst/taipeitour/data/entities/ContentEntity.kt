package com.cst.taipeitour.data.entities

import androidx.lifecycle.MutableLiveData

data class ContentEntity(

    val title: MutableLiveData<String> = MutableLiveData(""),
    val content: MutableLiveData<String> = MutableLiveData(""),
    val url: MutableLiveData<String> = MutableLiveData("")
)