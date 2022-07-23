package com.bn.flights.data.repository

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchRepositoryImpl @Inject constructor(
    private val launchDataSource: SpaceXDataSource
): LaunchRepository {
    override suspend fun getLaunches() = launchDataSource.getLaunches()
}