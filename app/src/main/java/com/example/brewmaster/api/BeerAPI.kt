package com.example.brewmaster.api

import com.example.brewmaster.model.Beer
import com.example.brewmaster.util.Constants.Companion.API_KEY
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface BeerAPI {

    @GET("beers")
    suspend fun getBeers(
        @Query("name") name: String,
        @Query("key") key: String = API_KEY
    ): Response<Beer>

    @GET("beers")
    suspend fun getAllBeers(
        @Query("order") orderCount: String = "random",
        @Query("key") key: String = API_KEY
    ): Response<Beer>

}