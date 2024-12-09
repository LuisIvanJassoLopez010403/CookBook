package com.example.cookbook.presentation.lists.models

data class UpdateListBody(
    val id: String,
    val nameList: String,
    val image: String,
    val description: String,
    val recipes: List<String>
)
