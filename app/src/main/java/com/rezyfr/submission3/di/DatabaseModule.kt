package com.rezyfr.submission3.di

import android.content.Context
import android.content.SharedPreferences
import com.rezyfr.submission3.data.database.SubmissionDatabase
import com.rezyfr.submission3.data.database.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        return context.getSharedPreferences(context.packageName + "_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): SubmissionDatabase {
        return SubmissionDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: SubmissionDatabase): FavoriteDao {
        return appDatabase.favoriteDao()
    }
}