package com.rezyfr.submission3.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.rezyfr.submission3.base.BaseDao
import com.rezyfr.submission3.data.entity.UserFavoriteEntity

@Dao
interface FavoriteDao : BaseDao<UserFavoriteEntity> {

    @Query("DELETE FROM UserFavoriteEntity WHERE id = :userId")
    fun deleteFavoriteUser(userId: Int): Int

    @Query("SELECT * FROM UserFavoriteEntity ORDER BY name ASC")
    fun getAllFavorites(): List<UserFavoriteEntity>

    @Query("SELECT * FROM UserFavoriteEntity WHERE id = :userId")
    fun getFavoriteByUserId(userId: Int): UserFavoriteEntity
}