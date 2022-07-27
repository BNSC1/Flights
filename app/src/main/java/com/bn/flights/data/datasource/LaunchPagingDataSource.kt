package com.bn.flights.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bn.flights.data.model.spaceX.Launch

class LaunchPagingDataSource(
    private val dataSource: SpaceXDataSource,
    private val sortOrder: SortOrder
) : PagingSource<Int, Launch>() {
    enum class SortOrder(val apiString: String) {
        ASCENDING("asc"),DESCENDING("desc")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
        val position = params.key ?: INITIAL_LOAD_POSITION
        val offset =
            params.key?.let { ((position - 1) * PAGE_SIZE) } ?: 0
        return try {
            val data = dataSource.getLaunches(
                start = offset,
                end = params.loadSize,
                order = sortOrder.apiString
            )

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