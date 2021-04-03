package com.rezyfr.submission3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rezyfr.submission3.data.database.dao.FavoriteDao
import com.rezyfr.submission3.data.entity.UserFavoriteEntity

@Database(version = 1, exportSchema = true, entities = [UserFavoriteEntity::class])
abstract class SubmissionDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var instance: SubmissionDatabase? = null

        fun getInstance(context: Context): SubmissionDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SubmissionDatabase::class.java,
                "Submission3.db"
            ).build()
    }

    abstract fun favoriteDao(): FavoriteDao
}