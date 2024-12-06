package com.example.cookbook.presentation.lists.models

data class UserListsResponse(
    val _id: String,
    val nameList: String,
    val description: String?,
    val recipes: List<Recipe>,
    val autor: String
)

data class Recipe(
    val _id: String,
    val nameRecipe: String,
    val preptime: Int,
    val description: String,
    val image: String
)
