package com.bn.flights.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    protected var job: Job? = null
    protected val _errorMsg = MutableStateFlow<String?>(null)
    val errorMsg = _errorMsg.asStateFlow()

    fun clearErrorMsg() {
        _errorMsg.value = null
    }

    protected inline fun tryRun(failAction: (Throwable) -> Unit = {}, action: () -> Unit) =
        runCatching {
            action()
        }.onFailure {
            failAction(it)
            _errorMsg.value = "${it.message}"
        }
}
