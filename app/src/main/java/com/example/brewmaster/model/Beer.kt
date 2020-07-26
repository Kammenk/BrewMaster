package com.example.brewmaster.model

data class Beer(
    val currentPage: Int,
    val `data`: List<Data>,
    val numberOfPages: Int,
    val totalResults: Int
)