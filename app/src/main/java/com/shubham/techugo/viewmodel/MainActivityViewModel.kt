package com.shubham.techugo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shubham.techugo.model.Resource
import com.shubham.techugo.network.TechugoRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainActivityViewModel(val repository: TechugoRepository) : ViewModel() {

    fun getDefaultData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = repository.getDefaultData()))
        } catch (exception : Exception){
            emit(Resource.error(data = null, message = exception.message ?: return@liveData))
        }
    }

}