package com.bn.flights.ui.launch

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.bn.flights.R
import com.bn.flights.data.model.spaceX.Launch
import com.bn.flights.databinding.FragmentLaunchListBinding
import com.bn.flights.ktx.collectLatestLifecycleFlow
import com.bn.flights.ui.base.CollectErrorNavigationFragment
import com.bn.flights.ui.base.OnItemClickListener
import com.bn.flights.ui.launch.adapter.LaunchListAdapter
import com.bn.flights.ui.launch.viewmodel.LaunchListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchListFragment : CollectErrorNavigationFragment<FragmentLaunchListBinding>() {
    override val viewModel: LaunchListViewModel by viewModels()
    private val launchListAdapter = setupLaunchListAdapter()
    private var sortOrder = LaunchListAdapter.SortOrder.OLDEST

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setupSortOrderSpinner()
            launchList.adapter = launchListAdapter
            setupScrollTopButton()
        }
        collectLaunches()
    }

    private fun FragmentLaunchListBinding.setupScrollTopButton() = scrollTopBtn.apply {
        setOnClickListener {
            launchList.smoothScrollToPosition(0)
        }
    }


    private fun FragmentLaunchListBinding.setupSortOrderSpinner() = sortOrderSpinner.apply {
        adapter = setupSpinnerAdapter()
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    LaunchListAdapter.SortOrder.OLDEST.ordinal -> {
                        sortOrder = LaunchListAdapter.SortOrder.OLDEST
                    }
                    LaunchListAdapter.SortOrder.NEWEST.ordinal -> {
                        sortOrder = LaunchListAdapter.SortOrder.NEWEST
                    }
                }
                sortList()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupSpinnerAdapter() =
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf(
                String.format(
                    getString(R.string.sort_label_format),
                    getString(R.string.flight_number),
                    getString(R.string.oldest)
                ),
                String.format(
                    getString(R.string.sort_label_format),
                    getString(R.string.flight_number),
                    getString(R.string.newest)
                ),

                )
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }


    private fun setupLaunchListAdapter() = LaunchListAdapter(object : OnItemClickListener<Launch> {
        override fun onItemClick(item: Launch) {
            LaunchListFragmentDirections.actionToLaunchDetailFragment(item).navigate()
        }
    })

    private fun collectLaunches() =
        viewModel.launchesFlow.collectLatestLifecycleFlow(viewLifecycleOwner) { data ->
            data?.let {
                launchListAdapter.replaceItems(it)
                sortList()
            }
        }

    private fun sortList() {
        launchListAdapter.sort(sortOrder)
    }
}