package com.cst.taipeitour.ui.main.webView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cst.taipeitour.data.config.BUNDLE_URL
import com.cst.taipeitour.databinding.FragmentWebViewBinding
import com.cst.taipeitour.ui.base.BaseFragment


class WebViewFragment : BaseFragment() {

    private lateinit var binding: FragmentWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWebViewBinding.inflate(inflater, container, false)

        arguments?.getString(BUNDLE_URL)?.let { setWebsite(it) }

        return binding.root
    }

    private fun setWebsite(
        url: String
    ) {

        with(binding.webView) {
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }
}