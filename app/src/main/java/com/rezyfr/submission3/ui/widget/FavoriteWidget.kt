package com.rezyfr.submission3.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.rezyfr.submission3.R
import com.rezyfr.submission3.ui.MainActivity

/**
 * Implementation of App Widget functionality.
 */
class FavoriteWidget : AppWidgetProvider() {

    companion object {
        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, FavoriteWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = toUri(Intent.URI_INTENT_SCHEME).toUri()
            }

            val widgetIntent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val widgetPendingIntent = PendingIntent.getActivity(context, 0, widgetIntent, 0)

            val views = RemoteViews(context.packageName, R.layout.favorite_widget).apply {
                setRemoteAdapter(R.id.sv_fav_widget, intent)
                setEmptyView(R.id.sv_fav_widget, R.id.tv_empty_widget)
                setTextViewText(R.id.tv_banner_widget, context.getString(R.string.appwidget_text))
                setOnClickPendingIntent(R.id.tv_banner_widget, widgetPendingIntent)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun sendRefreshBroadcast(context: Context?) {
            context?.let { ctx ->
                val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE).apply {
                    component = ComponentName(ctx, FavoriteWidget::class.java)
                }
                ctx.sendBroadcast(intent)
            }
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != null && intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val component = context?.let { ctx ->
                ComponentName(ctx, FavoriteWidget::class.java)
            }
            AppWidgetManager.getInstance(context).apply {
                notifyAppWidgetViewDataChanged(
                    getAppWidgetIds(component),
                    R.id.sv_fav_widget
                )
            }
        }
        intent?.let {
            if (it.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
                // refresh all your widgets
                val component = context?.let { context ->
                    ComponentName(
                        context,
                        FavoriteWidget::class.java
                    )
                }
                AppWidgetManager.getInstance(context).apply {
                    notifyAppWidgetViewDataChanged(
                        getAppWidgetIds(component),
                        R.id.sv_fav_widget
                    )
                }
            }
        }
        super.onReceive(context, intent)
    }
}
