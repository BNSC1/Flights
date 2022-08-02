package com.bn.flights.data.repository

import com.bn.flights.data.model.spaceX.Launch

interface LaunchRepository {
    suspend fun getLaunches() : List<Launch>
}