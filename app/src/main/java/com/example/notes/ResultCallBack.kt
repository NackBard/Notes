package com.example.notes

interface ResultCallBack {
    fun onSuccess(message: String)
    fun onError(message: String)
}