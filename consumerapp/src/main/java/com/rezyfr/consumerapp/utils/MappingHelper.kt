package com.rezyfr.consumerapp.utils

import android.content.ContentValues
import android.database.Cursor
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import java.util.*

object MappingHelper {

    fun Cursor.toFavoriteListEntity(): ArrayList<UserDetailResponse> {
        val favoriteEntityList = ArrayList<UserDetailResponse>()

        apply {
            while (moveToNext()) {
                favoriteEntityList.add(toFavoriteEntity())
            }
        }
        return favoriteEntityList
    }

    fun Cursor.toFavoriteEntity() : UserDetailResponse =
        UserDetailResponse(
            getString(getColumnIndexOrThrow(Constant.FAV_AVATAR_URL)),
            getString(getColumnIndexOrThrow(Constant.FAV_BIO)),
            getString(getColumnIndexOrThrow(Constant.FAV_CREATED_AT)),
            getString(getColumnIndexOrThrow(Constant.FAV_EMAIL)),
            getInt(getColumnIndexOrThrow(Constant.FAV_FOLLOWERS)),
            getInt(getColumnIndexOrThrow(Constant.FAV_FOLLOWING)),
            getInt(getColumnIndexOrThrow(Constant.FAV_ID)),
            getString(getColumnIndexOrThrow(Constant.FAV_LOCATION)),
            getString(getColumnIndexOrThrow(Constant.FAV_LOGIN)),
            getString(getColumnIndexOrThrow(Constant.FAV_NAME)),
            getString(getColumnIndexOrThrow(Constant.FAV_NODE_ID)),
            getInt(getColumnIndexOrThrow(Constant.FAV_PUBLIC_GISTS)),
            getInt(getColumnIndexOrThrow(Constant.FAV_PUBLIC_REPOS)),
            getString(getColumnIndexOrThrow(Constant.FAV_TYPE)),
            getString(getColumnIndexOrThrow(Constant.FAV_UPDATED_AT))
        )
}