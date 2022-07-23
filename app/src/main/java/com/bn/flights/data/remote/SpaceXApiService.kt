package com.bn.flights.data.remote

import com.bn.flights.data.model.spaceX.Launch
import javax.inject.Singleton

@Singleton
interface SpaceXApiService {
    suspend fun getLaunches(): List<Launch>
}