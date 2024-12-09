package com.example.cookbook.presentation.finder.models

import com.example.cookbook.Category
import com.example.cookbook.Ingredient

data class SearchRecipeBody(
    val _id: String,
    val nameRecipe: String,
    val description: String,
    val preptime: Int,
    val ingredients: List<Ingredient>,
    val steps: String,
    val createdDate: String,
    val category: Category,
    val autor: String,
    val image: String,
    val video: String,
    val grade: Double,
)