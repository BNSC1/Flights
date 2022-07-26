package com.bn.flights.ui

import android.os.Bundle
import com.bn.flights.R
import com.bn.flights.databinding.ActivityMainBinding
import com.bn.flights.ui.base.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NavigationActivity() {
    private lateinit var binding: ActivityMainBinding
    override val navHostId = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}