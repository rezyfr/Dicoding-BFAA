package com.rezyfr.submission3.utils

object Constant {
    const val ARGS_FRAGMENT_KEY = "args_fragment_key"
    const val ARGS_USERNAME = "args_username"

    const val AUTHORITY = "com.rezyfr.submission3"
    const val SCHEME = "content"
    const val TABLE_NAME = "favorite_entity"
    const val DATABASE_CONTENT_URI = "$SCHEME://$AUTHORITY"
    const val FAVORITE_CONTENT_URI = "$DATABASE_CONTENT_URI/$TABLE_NAME"

    const val FAV_ID = "id"
    const val FAV_AVATAR_URL = "avatar_url"
    const val FAV_BIO = "bio"
    const val FAV_CREATED_AT = "created_at"
    const val FAV_EMAIL = "email"
    const val FAV_FOLLOWERS = "followers"
    const val FAV_FOLLOWING = "following"
    const val FAV_LOCATION = "location"
    const val FAV_LOGIN = "login"
    const val FAV_NAME = "name"
    const val FAV_NODE_ID = "node_id"
    const val FAV_PUBLIC_GISTS = "public_gists"
    const val FAV_PUBLIC_REPOS = "public_repos"
    const val FAV_TYPE = "type"
    const val FAV_UPDATED_AT = "updated_at"

    const val ALARM_CHANNEL_ID = "alarm_channel"
    const val ALARM_CHANNEL_NAME = "Alarm Channel"
    const val ALARM_ID_REPEATING = 1
    const val ALARM_TITLE = "alarm_title"
    const val ALARM_MESSAGE = "alarm_message"

    const val PREF_REMINDER = "pref_reminder"
}