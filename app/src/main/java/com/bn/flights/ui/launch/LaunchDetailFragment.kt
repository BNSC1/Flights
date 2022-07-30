package com.bn.flights.ui.launch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bn.flights.R
import com.bn.flights.data.model.spaceX.Launch
import com.bn.flights.databinding.FragmentLaunchDetailBinding
import com.bn.flights.ui.base.NavigationFragment

class LaunchDetailFragment : NavigationFragment<FragmentLaunchDetailBinding>() {
    private val args: LaunchDetailFragmentArgs by navArgs()
    private lateinit var launch: Launch

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch = args.launch

        with(binding) {

        }
    }
}