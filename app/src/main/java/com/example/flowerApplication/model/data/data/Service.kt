package com.example.flowerApplication.model.data.data

import retrofit2.Response
import retrofit2.http.GET

interface Service {
    @GET("/api/json/get/cejknEwaZK?indent=2")
    suspend fun getFlowerData(): Response<List<DataClass>>
}