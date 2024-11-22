package com.example.cookbook.presentation.addrecipe.models

import com.example.cookbook.Category
import com.example.cookbook.Ingredient
import java.util.Date

data class RecipeBody(
    val nameRecipe: String,
    val preptime: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val image: String,
    val video: String,
    val category: Category,
    val autor: String,
    val calificacion: Double,
    val fecha: Date
)
