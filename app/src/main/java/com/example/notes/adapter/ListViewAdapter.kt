package com.example.notes.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.notes.R
import com.example.notes.model.Data

class ListViewAdapter(private var activity: Context, private var items: ArrayList<Data> ):
    BaseAdapter() {
    private class ViewHolder(row: View?) {
        var txtName: TextView? = null

        init {
            this.txtName = row?.findViewById<TextView>(R.id.txtName)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_view, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var userDto = items[position]
        viewHolder.txtName?.text = userDto.getText()

        return view as View
    }

    override fun getItem(i: Int): Data {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}

class UserDto {
    var name: String = ""

    constructor() {}

    constructor(name: String) {
        this.name = name
    }
}