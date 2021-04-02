package com.rezyfr.submission3.data.repository

import com.rezyfr.submission3.data.database.dao.FavoriteDao
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import javax.inject.Inject

interface FavoriteRepository {
    suspend fun addFavoriteUser(model: UserFavoriteEntity)
    suspend fun getFavoriteUsers(): List<UserFavoriteEntity>
}

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {

    override suspend fun addFavoriteUser(model: UserFavoriteEntity) {
        favoriteDao.insert(model)
    }

    override suspend fun getFavoriteUsers(): List<UserFavoriteEntity> {
        return favoriteDao.getAllFavorites()
    }
}