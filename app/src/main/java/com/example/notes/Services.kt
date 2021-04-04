package com.example.notes

import android.content.Intent
import android.widget.RemoteViewsService
import com.example.notes.adapter.ListViewAdapterWidget

class MyService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return ListViewAdapterWidget(applicationContext, intent)
    }
}