package com.example.cookbook

data class Category(
    val _id: String,
    val categoria: String?,
    val category: String?
) {
    val name: String
        get() = categoria ?: category ?: "Unknown"
}