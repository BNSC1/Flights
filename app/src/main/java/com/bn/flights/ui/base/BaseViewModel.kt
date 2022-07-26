package com.bn.flights.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {
    protected var job: Job? = null
    protected val _errorMsg = MutableStateFlow("")
    val errorMsg get() = _errorMsg
}
