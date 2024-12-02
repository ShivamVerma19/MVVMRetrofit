package com.example.tetsprep.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tetsprep.api.ApiInterface
import com.example.tetsprep.model.Jokes

class MemesRepository(private val apiInterface : ApiInterface) {

    private val memesLiveData = MutableLiveData<Jokes>()

    val memes : LiveData<Jokes>
    get() = memesLiveData

    suspend fun getMemes(){
        val result = apiInterface.getJokes()

        if(result.body() != null){
            memesLiveData.postValue(result.body())
        }
    }

}