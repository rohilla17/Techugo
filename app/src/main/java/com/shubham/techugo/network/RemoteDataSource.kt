package com.shubham.techugo.network

class RemoteDataSource(private val techugoAPI: TechugoAPI){

    suspend fun getDefaultData() = techugoAPI.getDefaultData()

}