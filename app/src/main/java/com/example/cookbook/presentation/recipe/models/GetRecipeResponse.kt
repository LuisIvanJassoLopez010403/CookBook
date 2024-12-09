package com.example.cookbook.presentation.recipe.models

import com.example.cookbook.Category
import com.example.cookbook.Ingredient

data class GetRecipeResponse(
    val _id: String,
    val nameRecipe: String,
    val preptime: Int,
    val ingredients: List<Ingredient>,
    val steps: String,
    val category: Category,
    val autor: Author,
    val __v: Int,
    val createdDate: String,
    val description: String,
    val image: String,
    val video: String,
)

data class Author(
    val _id: String,
    val username: String,
    val roll: String,
)