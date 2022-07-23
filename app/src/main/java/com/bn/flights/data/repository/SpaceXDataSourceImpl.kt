package com.bn.flights.data.repository

import com.bn.flights.data.remote.SpaceXApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpaceXDataSourceImpl @Inject constructor(private val spaceXApiService: SpaceXApiService) {
    suspend fun getLaunches() = spaceXApiService.getLaunches()
}