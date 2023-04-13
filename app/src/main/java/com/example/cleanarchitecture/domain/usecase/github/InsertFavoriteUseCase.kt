package com.example.cleanarchitecture.domain.usecase.github

import com.example.cleanarchitecture.data.room.favorite.FavoriteDao
import com.example.cleanarchitecture.data.room.favorite.FavoriteEntity

class InsertFavoriteUseCase(private val favoriteDao: FavoriteDao) {
    fun execute(entity: FavoriteEntity) = favoriteDao.insert(entity)
}