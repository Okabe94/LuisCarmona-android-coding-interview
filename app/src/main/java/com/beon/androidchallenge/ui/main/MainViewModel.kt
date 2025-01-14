package com.beon.androidchallenge.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beon.androidchallenge.data.repository.FactRepository
import com.beon.androidchallenge.domain.model.Fact

class MainViewModel : ViewModel() {

    val currentFact = MutableLiveData<Fact?>(null)

    fun searchNumberFact(number: String) {
        if (number.isEmpty()) {
            currentFact.postValue(null)
            return
        }

        FactRepository.getInstance().getFactForNumber(number, object : FactRepository.FactRepositoryCallback<Fact> {
            override fun onResponse(response: Fact) {
                currentFact.postValue(response)
            }

            override fun onError() {
                
            }

        })
    }
}