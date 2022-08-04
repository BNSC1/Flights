package com.bn.flights.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected var job: Job? = null
    protected val errorMsgChannel = Channel<String>()
    val errorMsgFlow = errorMsgChannel.receiveAsFlow()

    protected inline fun tryRun(failAction: (Throwable) -> Unit = {}, action: () -> Unit) =
        runCatching {
            action()
        }.onFailure {
            failAction(it)
            viewModelScope.launch {
                errorMsgChannel.send("${it.message}")
            }

        }
}
