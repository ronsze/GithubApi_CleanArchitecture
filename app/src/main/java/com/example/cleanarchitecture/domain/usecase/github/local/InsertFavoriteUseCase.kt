package com.example.cleanarchitecture.domain.usecase.github.local

import com.example.cleanarchitecture.data.room.favorite.FavoriteDao
import com.example.cleanarchitecture.data.room.favorite.FavoriteEntity

class InsertFavoriteUseCase(private val favoriteDao: FavoriteDao) {
    suspend fun execute(entity: FavoriteEntity) = favoriteDao.insert(entity)
}