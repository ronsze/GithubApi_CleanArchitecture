package com.example.cleanarchitecture.data.room.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    suspend fun insert(entity: FavoriteEntity)

    @Query("SELECT * FROM Favorite WHERE login = :login")
    suspend fun getFavoriteByLogin(login: String): FavoriteEntity?

    @Query("SELECT * FROM Favorite")
    suspend fun getAll(): List<FavoriteEntity>
}