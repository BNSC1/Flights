package com.bn.flights.data.repository

import com.bn.flights.data.datasource.SpaceXDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchRepositoryImpl @Inject constructor(
    private val spaceXDataSource: SpaceXDataSource
): LaunchRepository {
    override suspend fun getLaunches() = spaceXDataSource.getLaunches()
}