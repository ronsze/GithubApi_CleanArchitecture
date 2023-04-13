package com.example.cleanarchitecture.domain.usecase.github

import com.example.cleanarchitecture.data.room.favorite.FavoriteDao

class GetFavoriteListUseCase(private val favoriteDao: FavoriteDao) {
    fun execute() = favoriteDao.getAll()
}