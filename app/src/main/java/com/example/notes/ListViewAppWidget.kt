package com.example.notes

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class ListViewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (i in appWidgetIds) {
            updateWidget(context, appWidgetManager, i)
        }
    }

    fun updateWidget(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val rv = RemoteViews(
            context.packageName,
            R.layout.list_view_app_widget
        )
        setUpdateTV(rv, context, appWidgetId)
        setList(rv, context, appWidgetId)
        setListClick(rv, context, appWidgetId)
        appWidgetManager.updateAppWidget(appWidgetId, rv)
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId,
            R.id.list_item);
    }

    fun setUpdateTV(rv: RemoteViews, context: Context?, appWidgetId: Int) {
        val updIntent = Intent(context, ListViewAppWidget::class.java)
        updIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        updIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(appWidgetId))
        val updPIntent = PendingIntent.getBroadcast(
            context,
            appWidgetId, updIntent, 0
        )
        rv.setOnClickPendingIntent(R.id.refresh, updPIntent)
    }

    fun setList(rv: RemoteViews, context: Context?, appWidgetId: Int) {
        val adapter = Intent(context, MyService::class.java)
        adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        rv.setRemoteAdapter(R.id.list_item, adapter)
    }

    fun setListClick(rv: RemoteViews?, context: Context?, appWidgetId: Int) {}
}
