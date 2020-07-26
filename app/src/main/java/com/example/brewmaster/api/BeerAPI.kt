package com.example.brewmaster.api

import com.example.brewmaster.model.Beer
import io.reactivex.Observable
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
    ): Observable<List<Beer>>

    companion object {
        fun create(): BeerAPI {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                    GsonConverterFactory.create()
                )
                .baseUrl("https://sandbox-api.brewerydb.com/v2/")
                .build()

            return retrofit.create(BeerAPI::class.java)
        }
    }
}