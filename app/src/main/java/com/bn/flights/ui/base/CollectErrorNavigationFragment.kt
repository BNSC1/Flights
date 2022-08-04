package com.bn.flights.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.bn.flights.ktx.collectLifecycleFlow
import com.bn.flights.ktx.showToast

abstract class CollectErrorNavigationFragment<Binding : ViewBinding> :
    NavigationFragment<Binding>() {
    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectErrorMessage()
    }

    private fun collectErrorMessage() {
        viewModel.errorMsgFlow.collectLifecycleFlow(viewLifecycleOwner) { msg ->
                showToast(message = msg, Toast.LENGTH_LONG)
        }
    }
}