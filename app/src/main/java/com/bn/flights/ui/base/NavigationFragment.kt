package com.bn.flights.ui.base

import androidx.navigation.NavDirections
import androidx.viewbinding.ViewBinding

abstract class NavigationFragment<Binding : ViewBinding> : BaseFragment<Binding>() {
    private val mActivity get() = activity as? NavigationActivity

    private val navigation get() = mActivity?.navigation

    fun NavDirections.navigate() {
        navigation?.navigate(this)
    }
}