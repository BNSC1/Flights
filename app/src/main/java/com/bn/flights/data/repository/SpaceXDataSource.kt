package com.bn.flights.data.repository

import com.bn.flights.data.model.spaceX.Launch

interface SpaceXDataSource  {
    suspend fun getLaunches() : List<Launch>
}