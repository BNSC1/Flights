package com.bn.flights.ui.launch.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bn.flights.data.model.spaceX.Launch
import com.bn.flights.data.repository.LaunchRepository
import com.bn.flights.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val repository: LaunchRepository
) : BaseViewModel() {
    private lateinit var _launchesFlow: StateFlow<PagingData<Launch>?>
    val launchesFlow get() = _launchesFlow

    init {
        getLaunches()
    }

    private fun getLaunches() = viewModelScope.launch {
        try {
        _launchesFlow = repository.getLaunches().cachedIn(viewModelScope).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )
        } catch (e: Exception) {
            _errorMsg.value = e.message.toString()
        }
    }
}