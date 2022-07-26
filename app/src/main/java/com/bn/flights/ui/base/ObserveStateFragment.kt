package com.bn.flights.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.viewbinding.ViewBinding
import com.bn.flights.ktx.collectLifecycleFlow
import com.bn.flights.ktx.showToast

abstract class ObserveStateNavigationFragment<Binding: ViewBinding>: NavigationFragment<Binding>() {
    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectErrorMessage()
    }

    private fun collectErrorMessage() {
        viewModel.errorMsg.collectLifecycleFlow(this, Lifecycle.State.CREATED) {
            if (it.isNotBlank()) {
                showToast(message = it, Toast.LENGTH_LONG)
                viewModel.errorMsg.emit("")
            }
        }
    }
}