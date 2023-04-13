package com.example.cleanarchitecture.domain.usecase.github

import com.example.cleanarchitecture.data.room.favorite.FavoriteDao

class GetFavoriteByLoginUseCase(private val favoriteDao: FavoriteDao) {
    fun execute(login: String) = favoriteDao.getFavoriteByLogin(login)
}