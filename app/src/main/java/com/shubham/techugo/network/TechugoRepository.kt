package com.shubham.techugo.network

class TechugoRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getDefaultData() = remoteDataSource.getDefaultData()

}