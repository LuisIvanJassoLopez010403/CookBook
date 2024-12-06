package com.example.cookbook.presentation.lists.models

data class AddRecipeToListBody(
    val listId: String,
    val recipeId: String
)