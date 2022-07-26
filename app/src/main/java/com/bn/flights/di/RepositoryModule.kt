package com.bn.flights.di

import com.bn.flights.data.repository.LaunchRepository
import com.bn.flights.data.repository.LaunchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideLaunchRepository(repository: LaunchRepositoryImpl): LaunchRepository = repository
}