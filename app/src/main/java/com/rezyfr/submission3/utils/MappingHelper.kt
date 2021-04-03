package com.rezyfr.submission3.utils

import android.content.ContentValues
import android.database.Cursor
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import java.util.ArrayList

object MappingHelper {

    fun ContentValues.toFavoriteUserEntity() : UserFavoriteEntity =
        UserFavoriteEntity(
            avatar_url= getAsString(Constant.FAV_AVATAR_URL),
            bio = getAsString(Constant.FAV_BIO),
            created_at= getAsString(Constant.FAV_CREATED_AT),
            email= getAsString(Constant.FAV_EMAIL),
            followers=  getAsInteger(Constant.FAV_FOLLOWERS),
            following=  getAsInteger(Constant.FAV_FOLLOWING),
            id=  getAsInteger(Constant.FAV_ID),
            location= getAsString(Constant.FAV_LOCATION),
            login=  getAsString(Constant.FAV_LOGIN),
            name= getAsString(Constant.FAV_NAME),
            node_id= getAsString(Constant.FAV_NODE_ID),
            public_gists=  getAsInteger(Constant.FAV_PUBLIC_GISTS),
            public_repos=  getAsInteger(Constant.FAV_PUBLIC_REPOS),
            type= getAsString(Constant.FAV_TYPE),
            updated_at= getAsString(Constant.FAV_UPDATED_AT)
        )

    fun Cursor.toFavoriteListEntity(): ArrayList<UserFavoriteEntity> {
        val favoriteEntityList = ArrayList<UserFavoriteEntity>()

        apply {
            while (moveToNext()) {
                favoriteEntityList.add(toFavoriteEntity())
            }
        }
        return favoriteEntityList
    }

    fun Cursor.toFavoriteEntity() : UserFavoriteEntity =
        UserFavoriteEntity(
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