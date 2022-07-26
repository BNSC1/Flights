package com.bn.flights.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bn.flights.data.model.spaceX.Launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchPagingDataSource @Inject constructor(private val dataSource: SpaceXDataSource): PagingSource<Int, Launch>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
            val position = params.key ?: INITIAL_LOAD_POSITION
            val offset =
                params.key?.let { ((position - 1) * PAGE_SIZE)} ?: 0
            return try {
                val data = dataSource.getLaunches(start = offset, end = params.loadSize)

                val nextPageQuery = if (data.isEmpty()) {
                    null
                } else {
                    position + (params.loadSize / PAGE_SIZE)
                }

                LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = nextPageQuery
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Launch>): Int? = null

    companion object {
        private const val INITIAL_LOAD_POSITION = 1
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 2
    }
}