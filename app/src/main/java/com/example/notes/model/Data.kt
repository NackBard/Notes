package com.example.notes.model

import android.text.TextUtils
import androidx.core.view.accessibility.AccessibilityViewCommand
import androidx.databinding.BaseObservable

class Data (private var text: String, private var check: Int):BaseObservable() {

    val isDataValid: Boolean
        get() = (!TextUtils.isEmpty(text))

    fun getText():String{
        return text
    }

    fun setText(text: String){
        this.text = text
    }

    fun getCheck():Int{
        return check
    }

    fun setCheck(check: Int) {
        this.check = check
    }
}