package com.example.notes.adapter

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.notes.R
import com.example.notes.model.Data
import com.example.notes.model.DataBaseHelper

class ListViewAdapterWidget(context: Context, intent: Intent) :
    RemoteViewsService.RemoteViewsFactory {

    private lateinit var data: ArrayList<Data>
    private var context: Context
    private var widgetID: Int
    private var DB: DataBaseHelper

    init {
        this.context = context
        widgetID = intent.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        )
        DB = DataBaseHelper(context)
    }

    override fun onCreate() {
        data = ArrayList<Data>()
    }


    override fun onDataSetChanged() {
        data.clear()
        DB = DataBaseHelper(context)
        data = DB.selectTrue()
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        var remoteViews = RemoteViews(context.packageName, R.layout.list_view)
        remoteViews.setTextViewText(R.id.txtName, data!![position].getText())
        return remoteViews
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}