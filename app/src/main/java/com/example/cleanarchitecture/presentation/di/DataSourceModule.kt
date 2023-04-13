package com.example.cleanarchitecture.presentation.di

import com.example.cleanarchitecture.data.api.github.GithubService
import com.example.cleanarchitecture.data.api.github.RemoteGithubDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideGithubDataSource(githubService: GithubService): RemoteGithubDataSource =
        RemoteGithubDataSource(githubService)
}