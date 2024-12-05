package com.example.cookbook.presentation.finder.models

data class IngredientResponse (
    val category: String,
    val ingredients: List<SearchIngredient>
)