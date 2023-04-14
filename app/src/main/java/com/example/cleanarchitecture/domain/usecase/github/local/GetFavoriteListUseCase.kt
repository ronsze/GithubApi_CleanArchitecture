package com.example.cleanarchitecture.domain.usecase.github.local

import com.example.cleanarchitecture.data.room.favorite.FavoriteDao

class GetFavoriteListUseCase(private val favoriteDao: FavoriteDao) {
    suspend fun execute() = favoriteDao.getAll()
}