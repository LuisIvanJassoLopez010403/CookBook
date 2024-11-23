package com.example.cookbook.presentation.addrecipe.models

import com.example.cookbook.Ingredient
import java.util.Date

data class RecipeBody(
    val nameRecipe: String,
    val description: String,
    val preptime: Number,
    val ingredients: List<Ingredient>,
    val steps: String,
    val createdDate: String,
    val category: String,
    val autor: String,
    val image: String,
    val video: String,
    val grade: Double,
)
