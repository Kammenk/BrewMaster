package com.example.brewmaster.api

import com.example.brewmaster.model.beermodel.Beer
import com.example.brewmaster.model.brewerymodel.Brewery
import com.example.brewmaster.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerAPI {

    @GET("beers")
    suspend fun getBeers(
        @Query("name") name: String,
        @Query("key") key: String = API_KEY
    ): Response<Beer>

    @GET("breweries")
    suspend fun getBreweries(
        @Query("name") name: String,
        @Query("key") key: String = API_KEY
    ): Response<Brewery>

    @GET("beers")
    suspend fun getAllBeers(
        @Query("order") orderCount: String = "random",
        @Query("key") key: String = API_KEY
    ): Response<Beer>

}