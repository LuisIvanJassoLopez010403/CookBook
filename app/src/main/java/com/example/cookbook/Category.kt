package com.example.cookbook

data class Category(
    val _id: String,
    val categoria: String?,
    val category: String?,
    val icon: String?
) {
    val name: String
        get() = categoria ?: category ?: "Unknown"
}