package com.cst.taipeitour.data.entities

import androidx.lifecycle.MutableLiveData

data class MainEntity(
    val title: MutableLiveData<CharSequence> = MutableLiveData("")
)