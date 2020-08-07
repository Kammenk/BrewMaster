package com.example.brewmaster.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewmaster.model.beermodel.Beer
import com.example.brewmaster.model.brewerymodel.Brewery
import com.example.brewmaster.repository.BeerRepository
import com.example.brewmaster.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class BeerViewModel(var beerRepo: BeerRepository): ViewModel() {

    init {
    }

    val beers = MutableLiveData<Response<Beer>>()
    val allBeers = MutableLiveData<Response<Beer>>()
    val breweries = MutableLiveData<Response<Brewery>>()

    fun getBeers(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = beerRepo.getBeers(name)
            beers.postValue(response)
        }
    }

    fun getBreweries(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = beerRepo.getBreweries(name)
            breweries.postValue(response)
        }
    }

    fun getAllBeers(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = beerRepo.getAllBeers()
            allBeers.postValue(response)
        }
    }

    private fun handleBreakingNewsResponse(response: Response<Beer>): Resource<Beer>{
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}