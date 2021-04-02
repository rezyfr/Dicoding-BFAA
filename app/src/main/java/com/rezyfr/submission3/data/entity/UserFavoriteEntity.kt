package com.rezyfr.submission3.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserFavoriteEntity(
    val avatar_url: String?,
    val bio: String?,
    val created_at: String?,
    val email: String?,
    val followers: Int,
    val following: Int,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val location: String?,
    val login: String,
    val name: String?,
    val node_id: String?,
    val public_gists: Int,
    val public_repos: Int,
    val type: String?,
    val updated_at: String?
)
