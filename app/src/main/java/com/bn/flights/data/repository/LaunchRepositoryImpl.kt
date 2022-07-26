package com.bn.flights.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bn.flights.data.datasource.LaunchPagingDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchRepositoryImpl @Inject constructor(
    private val launchPagingDataSource: LaunchPagingDataSource
): LaunchRepository {
    override suspend fun getLaunches() = Pager(config = PagingConfig(
        pageSize = LaunchPagingDataSource.PAGE_SIZE,
        prefetchDistance = LaunchPagingDataSource.PREFETCH_DISTANCE
    ),
        pagingSourceFactory = { launchPagingDataSource }).flow
}