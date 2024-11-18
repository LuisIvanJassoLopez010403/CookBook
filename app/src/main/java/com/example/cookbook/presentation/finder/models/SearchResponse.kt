package com.example.cookbook.presentation.finder.models

import com.example.cookbook.RecipeBody

data class SearchResponse (
    val recipes: List<RecipeBody>
)