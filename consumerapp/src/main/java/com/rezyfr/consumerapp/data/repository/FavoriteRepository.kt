package com.rezyfr.consumerapp.data.repository

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import com.rezyfr.consumerapp.utils.Constant.FAVORITE_CONTENT_URI
import com.rezyfr.consumerapp.utils.MappingHelper.toFavoriteEntity
import com.rezyfr.consumerapp.utils.MappingHelper.toFavoriteListEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavoriteRepository {
    suspend fun addFavoriteUser(model: UserDetailResponse): LiveData<Long>
    suspend fun checkFavoriteUser(userId: Int): LiveData<UserDetailResponse?>
    suspend fun deleteFavoriteUser(user: UserDetailResponse): LiveData<Long>
    suspend fun getFavoriteUsers(): LiveData<List<UserDetailResponse>>
}

class FavoriteRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FavoriteRepository {

    override suspend fun addFavoriteUser(model: UserDetailResponse): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val cursor =
            context.contentResolver.insert(FAVORITE_CONTENT_URI.toUri(), model.toContentValues())

        cursor?.let { liveData.postValue(1) }

        return liveData
    }

    override suspend fun checkFavoriteUser(userId: Int): LiveData<UserDetailResponse?> {
        val liveData = MutableLiveData<UserDetailResponse?>()

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

    override suspend fun getFavoriteUsers(): LiveData<List<UserDetailResponse>> {
        val liveData = MutableLiveData<List<UserDetailResponse>>()

        val cursor = context.contentResolver
            .query(FAVORITE_CONTENT_URI.toUri(), null, null, null, null)

        cursor?.let {
            liveData.postValue(it.toFavoriteListEntity())
            cursor.close()
        }

        return liveData
    }

    override suspend fun deleteFavoriteUser(user: UserDetailResponse): LiveData<Long> {
        val liveData = MutableLiveData<Long>()

        val uri = "$FAVORITE_CONTENT_URI/${user.id}".toUri()
        val cursor =
            context.contentResolver.delete(uri, null, null)

        cursor.let { liveData.postValue(it.toLong()) }

        return liveData
    }
}