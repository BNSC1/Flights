package com.bn.flights.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlin.experimental.ExperimentalTypeInference

abstract class BaseViewModel : ViewModel() {
    protected var job: Job? = null
    private val _errorMsg = MutableStateFlow("")
    val errorMsg get() = _errorMsg
}
