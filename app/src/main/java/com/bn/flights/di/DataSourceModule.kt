package com.bn.flights.di

import com.bn.flights.data.datasource.SpaceXDataSource
import com.bn.flights.data.datasource.SpaceXDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideSpaceXDataSource(dataSource: SpaceXDataSourceImpl): SpaceXDataSource = dataSource
}