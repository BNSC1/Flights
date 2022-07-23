package com.bn.flights.di

import com.bn.flights.data.SPACEX_URL
import com.bn.flights.data.remote.DebugInterceptor
import com.bn.flights.data.repository.SpaceXDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(DebugInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideLaunchDataSource(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
    ): SpaceXDataSourceImpl =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SPACEX_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SpaceXDataSourceImpl::class.java)
}