package com.example.cleanarchitecture.domain.usecase.github.local

import com.example.cleanarchitecture.data.room.favorite.FavoriteDao

class GetFavoriteByLoginUseCase(private val favoriteDao: FavoriteDao) {
    suspend fun execute(login: String) = favoriteDao.getFavoriteByLogin(login)
}