package com.example.cleanarchitecture.data.room.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class FavoriteEntity (
    val login: String,
    val photo: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}