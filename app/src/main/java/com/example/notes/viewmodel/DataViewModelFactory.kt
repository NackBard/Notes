package com.example.notes.viewmodel

import android.service.carrier.CarrierMessagingService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.ResultCallBack

class DataViewModelFactory (private val listener: ResultCallBack) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(listener)as T
    }
}