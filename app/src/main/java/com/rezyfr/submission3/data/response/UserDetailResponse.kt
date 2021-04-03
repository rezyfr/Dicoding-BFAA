package com.rezyfr.submission3.data.response

import android.os.Parcelable
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class UserDetailResponse(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val company: String?,
    val created_at: String?,
    val email: String?,
    val events_url: String?,
    val followers: Int,
    val followers_url: String?,
    val following: Int,
    val following_url: String?,
    val gists_url: String?,
    val gravatar_id: String?,
    val hireable: String?,
    val html_url: String?,
    val id: Int,
    val location: String?,
    val login: String,
    val name: String?,
    val node_id: String?,
    val organizations_url: String?,
    val public_gists: Int,
    val public_repos: Int,
    val received_events_url: String?,
    val repos_url: String?,
    val site_admin: Boolean,
    val starred_url: String?,
    val subscriptions_url: String?,
    val twitter_username: String?,
    val type: String?,
    val updated_at: String?,
    val url: String?
) : Parcelable {
    fun toFavoriteEntity() = UserFavoriteEntity(
        avatar_url,
        bio,
        created_at,
        email,
        followers,
        following,
        id,
        location,
        login,
        name,
        node_id,
        public_gists,
        public_repos,
        type,
        updated_at
    )
}
