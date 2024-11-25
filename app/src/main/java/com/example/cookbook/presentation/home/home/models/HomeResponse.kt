package com.example.cookbook.presentation.home.home.models

import com.example.cookbook.presentation.finder.models.SearchRecipeBody

data class HomeResponse (
    val Category: String,
    val recipes: List<SearchRecipeBody>
)