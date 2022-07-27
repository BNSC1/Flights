package com.bn.flights.data.datasource

import com.bn.flights.data.remote.SpaceXApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpaceXDataSourceImpl @Inject constructor(private val spaceXApiService: SpaceXApiService): SpaceXDataSource {
    override suspend fun getLaunches(start: Int, end: Int, order: String) = spaceXApiService.getLaunches(start, end, order)
}