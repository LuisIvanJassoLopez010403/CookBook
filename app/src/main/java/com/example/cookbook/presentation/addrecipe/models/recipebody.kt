package com.example.cookbook.presentation.addrecipe.models

import com.example.cookbook.Ingredient

data class RecipeBody(
    val nameRecipe: String,
    val description: String,
    val preptime: Int,
    val ingredients: List<Ingredient>,
    val steps: String,
    val createdDate: String,
    val category: String,
    val autor: String,
    val image: String,
    val video: String,
    val grade: Double,
)
