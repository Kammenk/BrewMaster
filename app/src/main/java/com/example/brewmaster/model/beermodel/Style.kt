package com.example.brewmaster.model.beermodel

import com.example.brewmaster.model.beermodel.Category

data class Style(
    val abvMax: String,
    val abvMin: String,
    val category: Category,
    val categoryId: Int,
    val description: String,
    val fgMax: String,
    val fgMin: String,
    val ibuMax: String,
    val ibuMin: String,
    val id: Int,
    val name: String,
    val ogMin: String,
    val shortName: String,
    val srmMax: String,
    val srmMin: String
)