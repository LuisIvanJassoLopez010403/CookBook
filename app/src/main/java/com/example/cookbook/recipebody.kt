package com.example.cookbook

import com.example.cookbook.Ingredient
import java.util.Date

data class RecipeBody(
    val nameRecipe: String,
    val preptime: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val image: String,
    val video: String,
    val category: String,
    val autor: String,
    val calificacion: Double,
    val fecha: Date
)
