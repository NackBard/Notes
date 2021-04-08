package com.example.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProviders
import com.example.notes.databinding.AndriodAddTaskBinding
import com.example.notes.viewmodel.DataViewModel
import com.example.notes.viewmodel.DataViewModelFactory
import kotlinx.android.synthetic.main.toolbar.*

class AddNote: AppCompatActivity(), ResultCallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.andriod_add_task)

        val activityAddTaskBinding =
            setContentView<AndriodAddTaskBinding>(this, R.layout.andriod_add_task)
        activityAddTaskBinding.viewmodel =
            ViewModelProviders.of(this, DataViewModelFactory(this)).get(DataViewModel::class.java)

        add.visibility = View.GONE
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Cansel(v: View) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
