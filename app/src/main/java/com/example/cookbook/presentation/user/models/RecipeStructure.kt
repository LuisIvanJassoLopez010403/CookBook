package com.example.cookbook.presentation.user.models

data class RecipeStructure(
    val _id: String,
    val nameRecipe: String? = null,
    val description: String,
    val preptime: Int,
    val ingredients: List<IngredientStructure>,
    val steps: String,
    val createdDate: String,
    val category: String,
    val autor: String,
    val image: String,
    val video: String,
    val __v: Int
)