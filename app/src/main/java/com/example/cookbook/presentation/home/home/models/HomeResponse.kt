package com.example.cookbook.presentation.home.home.models

data class HomeResponse (
    val category: String,
    val recipes: List<HomeRecipeBody>
)