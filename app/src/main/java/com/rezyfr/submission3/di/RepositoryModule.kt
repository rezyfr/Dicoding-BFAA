package com.rezyfr.submission3.di

import com.rezyfr.submission3.data.database.dao.FavoriteDao
import com.rezyfr.submission3.data.repository.FavoriteRepository
import com.rezyfr.submission3.data.repository.FavoriteRepositoryImpl
import com.rezyfr.submission3.data.repository.UserRepository
import com.rezyfr.submission3.data.repository.UserRepositoryImpl
import com.rezyfr.submission3.network.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        githubApi: GithubApi
    ): UserRepository {
        return UserRepositoryImpl(githubApi)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        favoriteDao: FavoriteDao
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(favoriteDao)
    }
}