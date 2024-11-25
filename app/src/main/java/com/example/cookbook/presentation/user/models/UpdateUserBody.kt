package com.example.cookbook.presentation.user.models

data class UpdateUserBody(
    val userId: String,
    val bio: String,
    val profile_picture: String
)