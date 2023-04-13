package com.example.cleanarchitecture.presentation.di

import com.example.cleanarchitecture.data.room.favorite.FavoriteDao
import com.example.cleanarchitecture.domain.repository.github.GithubRepository
import com.example.cleanarchitecture.domain.usecase.github.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetUserInfoUseCase(githubRepository: GithubRepository): GetUserInfoUseCase = GetUserInfoUseCase(githubRepository)

    @Provides
    @Singleton
    fun provideGetFollowerListUseCase(githubRepository: GithubRepository): GetFollowerListUseCase = GetFollowerListUseCase(githubRepository)

    @Provides
    @Singleton
    fun provideInsertFavoriteUseCase(favoriteDao: FavoriteDao): InsertFavoriteUseCase = InsertFavoriteUseCase(favoriteDao)

    @Provides
    @Singleton
    fun provideGetFavoriteByLoginUseCase(favoriteDao: FavoriteDao): GetFavoriteByLoginUseCase = GetFavoriteByLoginUseCase(favoriteDao)

    @Provides
    @Singleton
    fun provideGetFavoriteListUseCase(favoriteDao: FavoriteDao): GetFavoriteListUseCase = GetFavoriteListUseCase(favoriteDao)
}