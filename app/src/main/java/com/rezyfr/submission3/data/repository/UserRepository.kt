package com.rezyfr.submission3.data.repository

import com.rezyfr.submission3.data.response.SearchResponse
import com.rezyfr.submission3.network.GithubApi
import javax.inject.Inject

interface UserRepository {
    suspend fun fetchUserList(query: String): SearchResponse
}

class UserRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
) : UserRepository {
    override suspend fun fetchUserList(query: String): SearchResponse {
        return githubApi.getSearchUser(query)
    }
}