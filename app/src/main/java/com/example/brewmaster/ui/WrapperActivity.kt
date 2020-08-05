package com.example.brewmaster.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.brewmaster.R
import com.example.brewmaster.repository.BeerRepository
import com.example.brewmaster.viewmodel.BeerViewModel
import com.example.brewmaster.viewmodel.BeerViewModelProviderFactory

class WrapperActivity : AppCompatActivity() {

    lateinit var viewModel: BeerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrapper)

        val beerRepo = BeerRepository()
        val beerViewModelProviderFactory = BeerViewModelProviderFactory(beerRepo)
        viewModel = ViewModelProvider(this,beerViewModelProviderFactory).get(BeerViewModel::class.java)

        setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}