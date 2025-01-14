package com.beon.androidchallenge.data.repository

import com.beon.androidchallenge.data.network.Api
import com.beon.androidchallenge.domain.model.Fact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactRemoteDataSource {

    fun getFactForNumber(number: String, callback: GetFactForNumberCallback) {
        Api.getInstance().getFactForNumber(number).enqueue(object : Callback<Fact> {
            override fun onResponse(call: Call<Fact>, response: Response<Fact>) {
                if (response.body() != null) {
                    callback.onFactLoaded(response.body()!!)
                } else {
                    callback.onFactLoadFailed()
                }
            }

            override fun onFailure(call: Call<Fact>, t: Throwable) {
                callback.onFactLoadFailed()
            }
        })
    }

    interface GetFactForNumberCallback {
        fun onFactLoaded(fact: Fact)
        fun onFactLoadFailed()
    }
}