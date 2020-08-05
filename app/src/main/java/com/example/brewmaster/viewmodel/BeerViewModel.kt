package com.example.brewmaster.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewmaster.model.Beer
import com.example.brewmaster.repository.BeerRepository
import com.example.brewmaster.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class BeerViewModel(var beerRepo: BeerRepository): ViewModel() {

//    init {
//        getAllBeers()
//    }

    val beers = MutableLiveData<Response<Beer>>() // = MutableLiveData<List<Beer>>()
    val allBeers = MutableLiveData<Response<Beer>>()

    fun getBeers(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = beerRepo.getBeers(name)
            beers.postValue(response)
            //beers.postValue(handleBreakingNewsResponse(response))
        }
    }

    fun getAllBeers(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = beerRepo.getAllBeers()
            allBeers.postValue(response)
            //beers.postValue(handleBreakingNewsResponse(response))
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