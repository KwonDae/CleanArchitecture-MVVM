package com.daewon.ohouseapp.di

import com.daewon.domain.repository.RemoteRepository
import com.daewon.domain.usecase.GetHomePageDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetHomePageDataUseCase(repository: RemoteRepository): GetHomePageDataUseCase {
        return GetHomePageDataUseCase(repository)
    }
}