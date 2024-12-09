package com.example.cookbook.presentation.home.home.models

import com.example.cookbook.Category
import com.example.cookbook.Ingredient

class HomeRecipeBody (
    val _id: String,
    val nameRecipe: String,
    val description: String,
    val preptime: Int,
    val ingredients: List<HomeIngredient>,
    val steps: String,
    val createdDate: String,
    val category: String,
    val autor: String,
    val image: String,
    val video: String,
    val grade: Double,
)