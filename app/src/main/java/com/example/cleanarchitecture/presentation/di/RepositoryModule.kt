package com.example.cleanarchitecture.presentation.di

import com.example.cleanarchitecture.data.api.github.GithubRepositoryImpl
import com.example.cleanarchitecture.data.api.github.RemoteGithubDataSource
import com.example.cleanarchitecture.domain.repository.github.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideGithubRepositoryImpl(githubDataSource: RemoteGithubDataSource): GithubRepository =
        GithubRepositoryImpl(githubDataSource)
}