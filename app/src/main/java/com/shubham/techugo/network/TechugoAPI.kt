package com.shubham.techugo.network

import com.shubham.techugo.model.StandardResponse
import retrofit2.http.GET

interface  TechugoAPI{

    @GET("talzo/dummy/test/testing_data")
    suspend fun getDefaultData() : StandardResponse

}