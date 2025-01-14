package com.beon.androidchallenge.data.network

import com.beon.androidchallenge.domain.model.Fact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FactsClient {
    @GET("{number}")
    @Headers("Content-Type: application/json")
    fun getFactForNumber(@Path("number") number: String): Call<Fact>
}