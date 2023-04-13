package com.example.cleanarchitecture.data.room.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
    fun insert(entity: FavoriteEntity)

    @Query("SELECT * FROM Favorite WHERE login = :login")
    fun getFavoriteByLogin(login: String): FavoriteEntity?

    @Query("SELECT * FROM Favorite")
    fun getAll(): List<FavoriteEntity>
}