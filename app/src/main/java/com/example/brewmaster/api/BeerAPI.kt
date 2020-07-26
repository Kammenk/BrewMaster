package com.example.brewmaster.api

import com.example.brewmaster.model.Beer
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface BeerAPI {

    @GET("beers")
    fun getBeers(
        @Query("name") name: String,
        @Query("key") key: String
    ): Response<Beer>

}