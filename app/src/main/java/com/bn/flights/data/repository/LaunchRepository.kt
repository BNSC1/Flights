package com.bn.flights.data.repository

import androidx.paging.PagingData
import com.bn.flights.data.model.spaceX.Launch
import dagger.Binds
import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {
    suspend fun getLaunches() : Flow<PagingData<Launch>>
}