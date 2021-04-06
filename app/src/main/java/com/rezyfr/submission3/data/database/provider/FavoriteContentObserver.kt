package com.rezyfr.submission3.data.database.provider

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.database.ContentObserver
import android.os.Handler
import com.rezyfr.submission3.R


class FavoriteContentObserver(
    private val mAppWidgetManager: AppWidgetManager,
    private val mComponentName: ComponentName,
    h: Handler?
) :
    ContentObserver(h) {
    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        mAppWidgetManager.notifyAppWidgetViewDataChanged(
            mAppWidgetManager.getAppWidgetIds(mComponentName), R.id.sv_fav_widget
        )
    }
}