package com.rezyfr.consumerapp.data.repository

import com.rezyfr.consumerapp.data.model.UserModel
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import com.rezyfr.consumerapp.network.GithubApi
import javax.inject.Inject

interface UserRepository {
    suspend fun fetchUserDetail(username: String): UserDetailResponse
    suspend fun fetchUserFollowing(username: String): List<UserModel>
    suspend fun fetchUserFollowers(username: String): List<UserModel>
}

class UserRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : UserRepository {

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