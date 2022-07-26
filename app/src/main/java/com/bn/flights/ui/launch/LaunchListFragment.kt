package com.bn.flights.ui.launch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
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
            launchList.adapter = launchListAdapter
        }
        collectLaunches()
    }

    private fun setupLaunchListAdapter() = LaunchListAdapter(object : OnItemClickListener<Launch> {
        override fun onItemClick(item: Launch) {
            //todo: move to detail
        }
    })

    private fun collectLaunches() =
        viewModel.launchesFlow.collectLatestLifecycleFlow(viewLifecycleOwner) { pagingData ->
            pagingData?.let {launchListAdapter.submitData(it)}
        }
}