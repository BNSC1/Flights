package com.bn.flights.ui.launch.viewmodel

import androidx.lifecycle.viewModelScope
import com.bn.flights.data.model.spaceX.Launch
import com.bn.flights.data.repository.LaunchRepository
import com.bn.flights.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(
    private val repository: LaunchRepository,
) : BaseViewModel() {
    private var _launchesFlow: StateFlow<List<Launch>?>
    val launchesFlow get() = _launchesFlow

    init {
        _launchesFlow = getLaunches()
    }

    private fun getLaunches() = flow {
        tryRun {
            emit(repository.getLaunches())
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = null
    )

}