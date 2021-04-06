package com.rezyfr.submission3.data.database.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.core.net.toUri
import com.rezyfr.submission3.SubmissionApp
import com.rezyfr.submission3.data.database.SubmissionDatabase
import com.rezyfr.submission3.ui.widget.FavoriteWidget
import com.rezyfr.submission3.utils.Constant.AUTHORITY
import com.rezyfr.submission3.utils.Constant.FAVORITE_CONTENT_URI
import com.rezyfr.submission3.utils.Constant.TABLE_NAME
import com.rezyfr.submission3.utils.MappingHelper.toFavoriteUserEntity

class FavoriteContentProvider : ContentProvider(){

    private var db: SubmissionDatabase? = null
    private var app: SubmissionApp? = null

    companion object {
        private const val USERS = 1
        private const val USER_ID = 2
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, USERS)
            uriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", USER_ID)
        }
    }

    override fun onCreate(): Boolean {
        context?.let { db = SubmissionDatabase.getInstance(it) }
        app = SubmissionApp()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            USERS -> db?.favoriteDao()?.getAllFavorites()
            USER_ID -> {
                uri.lastPathSegment?.toInt()?.let { id ->
                    db?.favoriteDao()?.getFavoriteByUserId(id)
                }
            }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // Implement this to handle requests to insert a new row.
        val added: Long = when (USERS) {
            uriMatcher.match(uri) -> values?.toFavoriteUserEntity()?.let {
                db?.favoriteDao()?.insert(it)
            } ?: 0
            else -> 0
        }

        context?.contentResolver?.notifyChange(FAVORITE_CONTENT_URI.toUri(), null)

//        refreshWidgetUser()

        return Uri.parse("$FAVORITE_CONTENT_URI/$added")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val deleted: Int = when (USER_ID) {
            uriMatcher.match(uri) -> {
                db?.favoriteDao()?.deleteFavoriteByUserId(
                    uri.lastPathSegment?.toInt() ?: 0
                ) ?: 0
            }
            else -> 0
        }

        context?.contentResolver?.notifyChange(FAVORITE_CONTENT_URI.toUri(), null)

//        refreshWidgetUser()

        return deleted
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        context?.contentResolver?.notifyChange(uri, null)
        return 0
    }

    private fun refreshWidgetUser() {
        app?.let { FavoriteWidget.sendRefreshBroadcast(it) }
    }
}