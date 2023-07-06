package com.cst.taipeitour.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.taipeitour.R
import com.cst.taipeitour.data.config.BUNDLE_DATA
import com.cst.taipeitour.databinding.FragmentListBinding
import com.cst.taipeitour.ui.base.BaseFragment
import com.cst.taipeitour.ui.main.MainViewModel
import com.cst.taipeitour.view.RecyclerDivider
import com.cst.taipeitour.view.snackbar
import org.kodein.di.instance

class ListFragment : BaseFragment() {

    private lateinit var binding: FragmentListBinding
    private val factory by instance<ListViewModelFactory>()
    private val viewModel by viewModels<ListViewModel> { factory }
    private val activityViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false).apply {

            with(recycler) {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
                addItemDecoration(RecyclerDivider(requireContext(), RecyclerDivider.Color.GRAY))
                adapter = viewModel.adapter
            }
        }

        with(viewModel) {
            throwMessage.observe(viewLifecycleOwner) { showThrowMessage(it) }
        }

        with(activityViewModel) {
            currentLang.observe(viewLifecycleOwner) { refreshList() }
        }

        return binding.root
    }

    private fun showThrowMessage(
        message: String
    ) {

        if (message.isBlank()) return

        binding.root.snackbar(message)

        viewModel.throwMessage.value = ""
    }

    private val itemClickListener = ListItem.ItemClickListener {

        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_DATA, it)
        findNavController().navigate(R.id.action_list_fragment_to_content_fragment, bundle)
    }

    private fun refreshList() = viewModel.getList(itemClickListener)
}