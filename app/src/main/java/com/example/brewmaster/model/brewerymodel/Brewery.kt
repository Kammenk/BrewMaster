package com.example.brewmaster.model.brewerymodel

data class Brewery(
    val currentPage: Int,
    val `data`: List<Data>,
    val numberOfPages: Int,
    val status: String,
    val totalResults: Int
)