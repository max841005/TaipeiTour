package com.cst.taipeitour.ui.main.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContentViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ContentViewModel() as T
}