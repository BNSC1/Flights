package com.bn.flights.ui.launch

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.bn.flights.data.datasource.LaunchPagingDataSource
import com.bn.flights.data.model.spaceX.Launch
import com.bn.flights.databinding.FragmentLaunchListBinding
import com.bn.flights.ktx.collectLatestLifecycleFlow
import com.bn.flights.ui.base.ObserveStateNavigationFragment
import com.bn.flights.ui.base.OnItemClickListener
import com.bn.flights.ui.launch.adapter.LaunchListAdapter
import com.bn.flights.ui.launch.viewmodel.LaunchListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchListFragment : ObserveStateNavigationFragment<FragmentLaunchListBinding>() {
    override val viewModel: LaunchListViewModel by viewModels()
    private val launchListAdapter = setupLaunchListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setupSortOrderSpinner()
            launchList.adapter = launchListAdapter
        }
        collectLaunches()
    }

    private fun FragmentLaunchListBinding.setupSortOrderSpinner() {
        sortOrderSpinner.adapter = setupSpinnerAdapter()
        sortOrderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        viewModel.setLaunchSortOrder(LaunchPagingDataSource.SortOrder.ASCENDING)
                        launchListAdapter.refresh()
                    }
                    1 -> {
                        viewModel.setLaunchSortOrder(LaunchPagingDataSource.SortOrder.DESCENDING)
                        launchListAdapter.refresh()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupSpinnerAdapter() =
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf("Flight number: Oldest",
                "Flight number: Newest")
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }


    private fun setupLaunchListAdapter() = LaunchListAdapter(object : OnItemClickListener<Launch> {
        override fun onItemClick(item: Launch) {
            LaunchListFragmentDirections.actionToLaunchDetailFragment(item).navigate()
        }
    })

    private fun collectLaunches() =
        viewModel.launchesFlow.collectLatestLifecycleFlow(viewLifecycleOwner) { pagingData ->
            pagingData?.let { launchListAdapter.submitData(it) }
        }
}