package com.example.cookbook.presentation.finder.models

import com.example.cookbook.presentation.addrecipe.models.RecipeBody

data class SearchResponse (
    val recipes: List<SearchRecipeBody>
)