package com.cst.taipeitour.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cst.taipeitour.data.repositories.MainRepository

class MainViewModelFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(mainRepository) as T
}