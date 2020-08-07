package com.example.brewmaster.model.beermodel

data class Data(
    val abv: String,
    val glass: Glass,
    val glasswareId: Int,
    val id: String,
    val isOrganic: String,
    val labels: Labels,
    val name: String,
    val nameDisplay: String,
    val style: Style,
    val styleId: Int
)