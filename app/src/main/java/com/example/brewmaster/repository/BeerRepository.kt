package com.example.brewmaster.repository

import com.example.brewmaster.api.BeerAPI
import com.example.brewmaster.api.RetrofitInstance
import com.example.brewmaster.model.Beer
import retrofit2.Call
import retrofit2.Response

class BeerRepository {

    suspend fun getBeers(name: String): Response<Beer> {
        return RetrofitInstance.api.getBeers(name)
    }

    suspend fun getAllBeers(): Response<Beer>{
        return getAllBeers()
    }
}