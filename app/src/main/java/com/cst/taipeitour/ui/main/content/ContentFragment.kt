package com.cst.taipeitour.ui.main.content

import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cst.taipeitour.R
import com.cst.taipeitour.data.config.BUNDLE_DATA
import com.cst.taipeitour.data.config.BUNDLE_URL
import com.cst.taipeitour.data.network.response.ResponseData
import com.cst.taipeitour.databinding.FragmentContentBinding
import com.cst.taipeitour.ui.base.BaseFragment
import com.denzcoskun.imageslider.models.SlideModel
import org.kodein.di.instance


class ContentFragment : BaseFragment() {

    private lateinit var binding: FragmentContentBinding
    private val factory by instance<ContentViewModelFactory>()
    private val viewModel by viewModels<ContentViewModel> { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContentBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner
            entity = viewModel.entity
            click = this@ContentFragment

            website.movementMethod = LinkMovementMethod.getInstance()
        }

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(BUNDLE_DATA, ResponseData.Data::class.java)
        }
        else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable(BUNDLE_DATA) as ResponseData.Data?
        }

        if (data != null) {
            setData(data)
        }

        return binding.root
    }

    override fun onClick(view: View) {
        super.onClick(view)

        when (view) {
            binding.website -> goToWebView()
        }
    }

    private fun setData(
        data: ResponseData.Data
    ) {
        viewModel.setData(data)
        binding.image.setImageList(data.images.map { SlideModel(it.src) })
    }

    private fun goToWebView() {

        val bundle = Bundle()
        bundle.putString(BUNDLE_URL, binding.website.text.toString())
        findNavController().navigate(R.id.action_content_fragment_to_web_view_fragment, bundle)
    }
}