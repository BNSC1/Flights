package com.bn.flights.data.remote

import com.bn.flights.data.model.spaceX.Launch
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface SpaceXApiService {
    @GET("launches")
    suspend fun getLaunches(): List<Launch>
}