package com.example.cookbook.presentation.user.models

data class UserDetailsResponse(
    val _id: String,
    val email: String,
    val username: String,
    val birthdate: String,
    val gender: String,
    val is_deleted: Boolean,
    val bio: String,
    val profile_picture: String,
    val roll: String
)