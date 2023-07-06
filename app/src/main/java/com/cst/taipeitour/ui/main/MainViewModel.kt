package com.cst.taipeitour.ui.main

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cst.taipeitour.data.config.BUNDLE_DATA
import com.cst.taipeitour.data.entities.MainEntity
import com.cst.taipeitour.data.network.response.ResponseData
import com.cst.taipeitour.data.repositories.MainRepository

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val entity = MainEntity()
    val currentLang = MutableLiveData(mainRepository.getLang())

    fun getCurrentLangItem() = mainRepository.getCurrentLangItem()

    fun changeLang(
        lang: String
    ) {
        mainRepository.changeLang(lang)
        currentLang.value = mainRepository.getLang()
    }

    fun setTitle(
        bundle: Bundle
    ) {

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(BUNDLE_DATA, ResponseData.Data::class.java)
        }
        else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(BUNDLE_DATA) as ResponseData.Data?
        }

        if (data != null) {
            entity.title.value = data.name
        }
    }
}
