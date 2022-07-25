package com.bn.flights.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import java.lang.reflect.ParameterizedType

abstract class NavigationFragment<Binding : ViewBinding> : BaseFragment<Binding>() {
    private val mActivity get() = activity as? NavigationActivity

    private val navigation get() = mActivity?.navigation

    fun NavDirections.navigate() {
        navigation?.navigate(this)
    }
}