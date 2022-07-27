package com.bn.flights.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bn.flights.data.datasource.LaunchPagingDataSource
import com.bn.flights.data.datasource.SpaceXDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchRepositoryImpl @Inject constructor(
    private val spaceXDataSource: SpaceXDataSource
): LaunchRepository {
    override suspend fun getLaunches() = Pager(config = PagingConfig(
        pageSize = LaunchPagingDataSource.PAGE_SIZE,
        prefetchDistance = LaunchPagingDataSource.PREFETCH_DISTANCE
    ),
        pagingSourceFactory = { LaunchPagingDataSource(spaceXDataSource, sortOrder) }).flow

    override fun setLaunchSortOrder(sortOrder: LaunchPagingDataSource.SortOrder) {
//        launchPagingDataSource.sortOrder = sortOrder
        this.sortOrder = sortOrder
    }

    var sortOrder = LaunchPagingDataSource.SortOrder.ASCENDING
}