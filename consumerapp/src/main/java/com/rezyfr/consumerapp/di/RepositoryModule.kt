package com.rezyfr.consumerapp.di

import android.content.Context
import com.rezyfr.consumerapp.data.repository.FavoriteRepository
import com.rezyfr.consumerapp.data.repository.FavoriteRepositoryImpl
import com.rezyfr.consumerapp.data.repository.UserRepository
import com.rezyfr.consumerapp.data.repository.UserRepositoryImpl
import com.rezyfr.consumerapp.network.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        @ApplicationContext context: Context
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(context)
    }
}