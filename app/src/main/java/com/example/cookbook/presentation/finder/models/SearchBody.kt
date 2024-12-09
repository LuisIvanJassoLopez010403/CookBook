package com.example.cookbook.presentation.finder.models

import com.example.cookbook.Category
import com.example.cookbook.Ingredient
import com.example.cookbook.IngredientDetails

data class SearchBody (
    val nameRecipe: String,
    val ingredients: List<String>,
    val category: List<String>,
    )
