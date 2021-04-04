package com.rezyfr.consumerapp.data.response

import android.content.ContentValues
import android.os.Parcelable
import com.rezyfr.consumerapp.utils.Constant
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailResponse(
    val avatar_url: String?,
    val bio: String?,
    val created_at: String?,
    val email: String?,
    val followers: Int,
    val following: Int,
    val id: Int,
    val location: String?,
    val login: String,
    val name: String?,
    val node_id: String?,
    val public_gists: Int,
    val public_repos: Int,
    val type: String?,
    val updated_at: String?,
) : Parcelable{

    fun toContentValues() = ContentValues().apply {
        put(Constant.FAV_AVATAR_URL, avatar_url)
        put(Constant.FAV_BIO, bio)
        put(Constant.FAV_CREATED_AT, created_at)
        put(Constant.FAV_EMAIL, email)
        put(Constant.FAV_FOLLOWERS, followers)
        put(Constant.FAV_FOLLOWING, following)
        put(Constant.FAV_ID, id)
        put(Constant.FAV_LOCATION, location)
        put(Constant.FAV_LOGIN, login)
        put(Constant.FAV_NAME, name)
        put(Constant.FAV_NODE_ID, node_id)
        put(Constant.FAV_PUBLIC_GISTS, public_gists)
        put(Constant.FAV_PUBLIC_REPOS, public_repos)
        put(Constant.FAV_TYPE, type)
        put(Constant.FAV_UPDATED_AT, updated_at)
    }
}
