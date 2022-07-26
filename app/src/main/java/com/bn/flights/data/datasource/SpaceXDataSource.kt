package com.bn.flights.data.datasource

import com.bn.flights.data.model.spaceX.Launch

interface SpaceXDataSource {
    suspend fun getLaunches(start: Int, end: Int) : List<Launch>
}