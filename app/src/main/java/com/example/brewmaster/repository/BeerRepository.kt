package com.example.brewmaster.repository

import com.example.brewmaster.api.RetrofitInstance
import com.example.brewmaster.model.beermodel.Beer
import com.example.brewmaster.model.brewerymodel.Brewery
import retrofit2.Response

class BeerRepository {

    suspend fun getBeers(name: String): Response<Beer> {
        return RetrofitInstance.api.getBeers(name)
    }

    suspend fun getBreweries(name: String): Response<Brewery> {
        return RetrofitInstance.api.getBreweries(name)
    }

    suspend fun getAllBeers(): Response<Beer>{
        return getAllBeers()
    }
}