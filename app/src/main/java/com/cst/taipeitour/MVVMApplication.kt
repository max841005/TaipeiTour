package com.cst.taipeitour

import android.app.Application
import com.cst.taipeitour.data.network.ApiService
import com.cst.taipeitour.data.network.NetworkConnectionInterceptor
import com.cst.taipeitour.data.preferences.PreferenceProvider
import com.cst.taipeitour.data.repositories.MainRepository
import com.cst.taipeitour.ui.main.MainViewModelFactory
import com.cst.taipeitour.ui.main.content.ContentViewModelFactory
import com.cst.taipeitour.ui.main.list.ListViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

class MVVMApplication : Application(), DIAware {

    override val di by DI.lazy {

        import(androidXModule(this@MVVMApplication))

        bind { singleton { PreferenceProvider(instance()) } }
        bind { singleton { NetworkConnectionInterceptor(instance()) } }
        bind { singleton { ApiService(instance()) } }

        //Repository
        bind { singleton { MainRepository(instance(), instance()) } }

        //Factory
        bind { provider { MainViewModelFactory(instance()) } }
        bind { provider { ListViewModelFactory(instance()) } }
        bind { provider { ContentViewModelFactory() } }
    }
}