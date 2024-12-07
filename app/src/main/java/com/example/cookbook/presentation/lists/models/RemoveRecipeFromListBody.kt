package com.example.cookbook.presentation.lists.models

data class RemoveRecipeFromListBody(
    val listId: String,
    val recipeId: String
)