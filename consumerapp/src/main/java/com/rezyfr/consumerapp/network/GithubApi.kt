package com.rezyfr.consumerapp.network

import com.rezyfr.consumerapp.data.model.UserModel
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username: String
    ): UserDetailResponse

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username: String
    ): List<UserModel>

    @GET("users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") username: String
    ): List<UserModel>
}