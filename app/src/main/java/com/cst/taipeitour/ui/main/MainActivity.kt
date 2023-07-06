package com.cst.taipeitour.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.cst.taipeitour.R
import com.cst.taipeitour.databinding.ActivityMainBinding
import com.cst.taipeitour.ui.base.BaseActivity
import com.cst.taipeitour.utils.toLang
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.kodein.di.instance
import java.util.Locale

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val factory: MainViewModelFactory by instance()
    private val viewModel by viewModels<MainViewModel> { factory }

    private val fragment: NavHostFragment by lazy { supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment }

    private val dialogBuilder by lazy { MaterialAlertDialogBuilder(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {

            lifecycleOwner = this@MainActivity
            click = this@MainActivity
            entity = viewModel.entity

            with(toolbar) {
                setNavigationOnClickListener { this@MainActivity.fragment.navController.popBackStack() }
                setOnMenuItemClickListener {

                    if (it.itemId == R.id.change_lang) {
                        showLangDialog()
                        true
                    }
                    else {
                        false
                    }
                }
            }
        }

        fragment.navController.addOnDestinationChangedListener { _, destination, bundle ->
            destinationChange(destination, bundle)
        }

        viewModel.currentLang.observe(this) {
            setLang(it)
            viewModel.entity.title.value = getString(R.string.app_name)
        }
    }

    private fun setLang(
        lang: Locale
    ) {

        val config = resources.configuration
        config.setLocale(lang)

        @Suppress("DEPRECATION")
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun destinationChange(
        destination: NavDestination,
        bundle: Bundle?
    ) {

        when (destination.id) {

            R.id.list_fragment -> {

                viewModel.entity.title.value = getString(R.string.app_name)

                with(binding.toolbar) {
                    navigationIcon = null
                    menu.getItem(0).isVisible = true
                }
            }

            else -> {

                bundle?.let { viewModel.setTitle(it) }

                with(binding.toolbar) {
                    setNavigationIcon(R.drawable.icon_arrow_back)
                    menu.getItem(0).isVisible = false
                }
            }
        }
    }

    private fun showLangDialog() {

        dialogBuilder.setSingleChoiceItems(
            resources.getStringArray(R.array.lang_array),
            viewModel.getCurrentLangItem()
        ) { dialog, which ->

            val lang = which.toLang()
            viewModel.changeLang(lang)

            dialog.dismiss()

        }.show()
    }
}