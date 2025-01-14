package com.beon.androidchallenge.data.repository

import com.beon.androidchallenge.domain.model.Fact

class FactRepository(
    private var factRemoteDataSource: FactRemoteDataSource,
    private var factLocalDataSource: FactLocalDataSource
) {

    companion object {
        private var INSTANCE: FactRepository? = null

        fun getInstance(): FactRepository {
            if (INSTANCE == null) {
                INSTANCE = FactRepository(FactRemoteDataSource(), FactLocalDataSource())
            }
            return INSTANCE!!
        }
    }

    fun getFactForNumber(number: String, callback: FactRepositoryCallback<Fact>) {
        factRemoteDataSource.getFactForNumber(number,
            object : FactRemoteDataSource.GetFactForNumberCallback {
                override fun onFactLoaded(fact: Fact) {
                    callback.onResponse(fact)
                }

                override fun onFactLoadFailed() {
                    callback.onError()
                }
            })
    }

    interface FactRepositoryCallback<T> {
        fun onResponse(response: T)
        fun onError()
    }

}