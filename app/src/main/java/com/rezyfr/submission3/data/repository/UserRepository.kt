package com.rezyfr.submission3.data.repository

import com.rezyfr.submission3.data.model.UserModel
import com.rezyfr.submission3.data.response.SearchResponse
import com.rezyfr.submission3.data.response.UserDetailResponse
import com.rezyfr.submission3.network.GithubApi
import javax.inject.Inject

interface UserRepository {
    suspend fun fetchUserList(query: String): SearchResponse
    suspend fun fetchUserDetail(username: String): UserDetailResponse
    suspend fun fetchUserFollowing(username: String): List<UserModel>
    suspend fun fetchUserFollowers(username: String): List<UserModel>
}

class UserRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : UserRepository {
    override suspend fun fetchUserList(query: String): SearchResponse {
        return githubApi.getSearchUser(query)
    }

    override suspend fun fetchUserDetail(username: String): UserDetailResponse {
        return githubApi.getUserDetail(username)
    }

    override suspend fun fetchUserFollowing(username: String): List<UserModel> {
        return githubApi.getUserFollowing(username)
    }

    override suspend fun fetchUserFollowers(username: String): List<UserModel> {
        return githubApi.getUserFollowers(username)
    }
}