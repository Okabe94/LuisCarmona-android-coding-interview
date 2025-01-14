package com.beon.androidchallenge.data.network

import com.beon.androidchallenge.domain.model.Fact
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private var API_BASE_URL = "http://numbersapi.com/"
    var client: FactsClient

    companion object {
        private var INSTANCE: Api? = null

        fun getInstance(): Api {
            if (INSTANCE == null) {
                INSTANCE = Api()
            }
            return INSTANCE!!
        }
    }

    init {
        val httpClient = OkHttpClient.Builder()

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        client = retrofit.create(FactsClient::class.java)
    }

    fun getFactForNumber(number: String): Call<Fact> {
        return client.getFactForNumber(number)
    }
}