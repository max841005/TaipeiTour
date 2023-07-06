package com.cst.taipeitour.ui.main.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cst.taipeitour.data.repositories.MainRepository
import com.cst.taipeitour.utils.Coroutines.io
import com.cst.taipeitour.utils.Coroutines.main
import com.xwray.groupie.GroupieAdapter

class ListViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val throwMessage = MutableLiveData("")

    val adapter = GroupieAdapter()

    fun getList(
        itemClickListener: ListItem.ItemClickListener
    ) = io {

        runCatching {
            mainRepository.getList()
        }.onSuccess { dataList ->

            main {

                with(adapter) {
                    clear()
                    addAll(dataList.map { data -> ListItem(data, itemClickListener) })
                }
            }

        }.onFailure {

            it.printStackTrace()

            main {

                adapter.clear()

                throwMessage.value = it.message
            }
        }
    }
}