package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.notes.adapter.ListViewAdapter
import com.example.notes.model.DataBaseHelper

class MainActivity : AppCompatActivity() {
    var adapter: ListViewAdapter? = null
    var DB = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cancel_button.visibility = View.GONE

        var listView: ListView? = null
        listView = findViewById<ListView>(R.id.listview)

        adapter = ListViewAdapter(this,DB.select())

        listView?.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    override fun onRestart() {
        adapter?.notifyDataSetChanged()
        super.onRestart()
    }

        fun addNote(v: View) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        var intent = Intent(this, AddNote::class.java)
        startActivity(intent)
    }


}