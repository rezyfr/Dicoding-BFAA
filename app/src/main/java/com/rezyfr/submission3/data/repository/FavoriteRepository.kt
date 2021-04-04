package com.rezyfr.submission3.data.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezyfr.submission3.data.database.dao.FavoriteDao
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import com.rezyfr.submission3.utils.Constant.FAVORITE_CONTENT_URI
import com.rezyfr.submission3.utils.MappingHelper.toFavoriteEntity
import com.rezyfr.submission3.utils.MappingHelper.toFavoriteListEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavoriteRepository {
    suspend fun addFavoriteUser(model: UserFavoriteEntity): LiveData<Long>
    suspend fun checkFavoriteUser(userId: Int): LiveData<UserFavoriteEntity?>
    suspend fun deleteFavoriteUser(user: UserFavoriteEntity): LiveData<Long>
    suspend fun getFavoriteUsers(): LiveData<List<UserFavoriteEntity>>
}

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    @ApplicationContext private val context: Context
) : FavoriteRepository {

    override suspend fun addFavoriteUser(model: UserFavoriteEntity): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val cursor =
            context.contentResolver.insert(FAVORITE_CONTENT_URI.toUri(), model.toContentValues())

        cursor?.let { liveData.postValue(1) }

        return liveData
//        favoriteDao.insert(model)
    }

    override suspend fun checkFavoriteUser(userId: Int): LiveData<UserFavoriteEntity?> {
        val liveData = MutableLiveData<UserFavoriteEntity?>()

        val uri = "$FAVORITE_CONTENT_URI/$userId".toUri()
        val cursor = context.contentResolver.query(uri, null, null, null, null)

        withContext(Dispatchers.Main) {
            cursor?.let {
                if (cursor.moveToFirst()) {
                    liveData.value = it.toFavoriteEntity()
                } else {
                    liveData.value = null
                }
                cursor.close()
            }
        }

        return liveData
    }

    override suspend fun getFavoriteUsers(): LiveData<List<UserFavoriteEntity>> {
        val liveData = MutableLiveData<List<UserFavoriteEntity>>()

        val cursor = context.contentResolver
            .query(FAVORITE_CONTENT_URI.toUri(), null, null, null, null)

        cursor?.let {
            liveData.postValue(it.toFavoriteListEntity())
            cursor.close()
        }

        return liveData
//        return favoriteDao.getAllFavorites()
    }

    override suspend fun deleteFavoriteUser(user: UserFavoriteEntity): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val uri = "$FAVORITE_CONTENT_URI/${user.id}".toUri()
        val cursor =
            context.contentResolver.delete(uri, null, null)

        cursor.let { liveData.postValue(it.toLong()) }

        return liveData
    }
}