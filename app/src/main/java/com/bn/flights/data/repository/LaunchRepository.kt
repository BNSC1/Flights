package com.bn.flights.data.repository

import androidx.paging.PagingData
import com.bn.flights.data.datasource.LaunchPagingDataSource
import com.bn.flights.data.model.spaceX.Launch
import kotlinx.coroutines.flow.Flow

interface LaunchRepository {
    suspend fun getLaunches() : Flow<PagingData<Launch>>

    fun setLaunchSortOrder(sortOrder: LaunchPagingDataSource.SortOrder)
}