package com.example.cookbook

data class Ingredient(
    val _idIngredient: IngredientDetails,
    val unit: String,
    val amount: Double
)

data class IngredientDetails(
    val _id: String,
    val nameIngredient: String,
    val categoy: String,
    val __v: Int
)