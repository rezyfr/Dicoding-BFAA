package com.rezyfr.submission3.ui.widget

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rezyfr.submission3.R
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import com.rezyfr.submission3.utils.Constant.FAVORITE_CONTENT_URI
import com.rezyfr.submission3.utils.MappingHelper.toFavoriteListEntity
import java.util.concurrent.ExecutionException


internal class FavoriteWidgetRemoteFactory(private val ctx: Context) : RemoteViewsService.RemoteViewsFactory  {

    private var widgetItem = arrayListOf<UserFavoriteEntity>()
    private var cursor: Cursor? = null

    override fun onCreate() {
        // No OP
    }

    override fun onDataSetChanged() {
        cursor?.close()
        val identityToken = Binder.clearCallingIdentity()

        cursor = ctx.contentResolver?.query(FAVORITE_CONTENT_URI.toUri(), null, null, null, null)
        cursor?.let{
            widgetItem = it.toFavoriteListEntity()
        }
        Binder.restoreCallingIdentity(identityToken)
    }

    override fun onDestroy() {
        cursor?.close()
        widgetItem = arrayListOf()
    }

    override fun getCount(): Int = widgetItem.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(ctx.packageName, R.layout.favorite_widget_item_layout)
        if (!widgetItem.isNullOrEmpty()) {
            try {
                val preview: Bitmap = Glide.with(ctx)
                    .asBitmap()
                    .load(widgetItem[position].avatar_url)
                    .apply(RequestOptions().fitCenter())
                    .submit()
                    .get()
                rv.setImageViewBitmap(R.id.iv_avatar_widget, preview)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: ExecutionException) {
                e.printStackTrace()
            }
            rv.setTextViewText(
                R.id.tv_name_widget, widgetItem[position].name
            )
            rv.setTextViewText(
                R.id.tv_username_widget, widgetItem[position].login
            )
        }

        return rv
    }

    override fun getLoadingView(): RemoteViews? =null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = true
}