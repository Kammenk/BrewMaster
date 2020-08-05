package com.example.brewmaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brewmaster.repository.BeerRepository

class BeerViewModelProviderFactory(val beerRepo: BeerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerViewModel(beerRepo) as T
    }
}