package com.example.cookbook.presentation.user.models

typealias HistoryResponse = List<HistoryItem>

data class HistoryItem(
    val _id: String,
    val userId: String,
    val recipeHistory: List<RecipeHistory>,
    val __v: Int
)

data class RecipeHistory(
    val recipeId: RecipeStructure,
    val date: String,
    val _id: String
)
