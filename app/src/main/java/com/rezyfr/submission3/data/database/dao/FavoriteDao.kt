package com.rezyfr.submission3.data.database.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Query
import com.rezyfr.submission3.base.BaseDao
import com.rezyfr.submission3.data.entity.UserFavoriteEntity

@Dao
interface FavoriteDao : BaseDao<UserFavoriteEntity> {

    @Query("DELETE FROM favorite_entity WHERE id = :userId")
    fun deleteFavoriteByUserId(userId: Int): Int

    @Query("SELECT * FROM favorite_entity ORDER BY name ASC")
    fun getAllFavorites(): Cursor

    @Query("SELECT * FROM favorite_entity WHERE id = :userId")
    fun getFavoriteByUserId(userId: Int): Cursor
}