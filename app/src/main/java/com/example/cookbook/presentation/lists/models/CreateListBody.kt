package com.example.cookbook.presentation.lists.models

data class CreateListBody(
    val nameList: String,
    val recipes: List<String>,
    val autor: String,
    val description: String,
    val image: String
)