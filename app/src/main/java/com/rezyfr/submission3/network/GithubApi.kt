package com.rezyfr.submission3.network

import com.rezyfr.submission3.data.model.UserModel
import com.rezyfr.submission3.data.response.SearchResponse
import com.rezyfr.submission3.data.response.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") keyword: String
    ): SearchResponse

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