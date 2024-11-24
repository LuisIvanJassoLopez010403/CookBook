package com.example.cookbook.presentation.user.models

typealias HistoryResponse = List<HistoryItem>

data class HistoryItem(
    val _id: String,
    val userId: String,
    val recipeHistory: List<RecipeHistory>,
    val __v: Int
)

/*data class HistoryResponse(
    val _id: String,
    val userId: String,
    val recipeHistory: List<RecipeHistory>,
    val __v: Int
)*/

data class RecipeHistory(
    val recipeId: RecipeStructure,
    val date: String,
    val _id: String
)

data class RecipeStructure(
    val _id: String,
    val nameRecipe: String,
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

data class IngredientStructure(
    val _idIngredient: String,
    val unit: String,
    val amount: Double,
    val _id: String
)

