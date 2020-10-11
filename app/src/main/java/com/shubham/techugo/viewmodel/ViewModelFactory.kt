package com.shubham.techugo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shubham.techugo.network.RemoteDataSource
import com.shubham.techugo.network.TechugoRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val remoteDataSource: RemoteDataSource) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) return MainActivityViewModel(
            TechugoRepository(remoteDataSource)
        ) as T
        throw IllegalArgumentException("Unknown class name")
    }

}
