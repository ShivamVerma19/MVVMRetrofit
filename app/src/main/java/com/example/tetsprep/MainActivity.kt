package com.example.tetsprep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.tetsprep.api.ApiInterface
import com.example.tetsprep.api.ApiUtilities
import com.example.tetsprep.repository.MemesRepository
import com.example.tetsprep.viewmodel.MemesViewModel
import com.example.tetsprep.viewmodel.MemesViewModelFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var memesViewModel: MemesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val memesRepository = MemesRepository(apiInterface)

        memesViewModel = ViewModelProvider(this , MemesViewModelFactory(memesRepository))[MemesViewModel::class.java]

        memesViewModel.memes.observe(this , {
            it.data.memes.iterator().forEach {meme ->
                Log.d("Shivam" , "name : ${meme.name}  image : ${meme.url}")
            }
        })
    }
}