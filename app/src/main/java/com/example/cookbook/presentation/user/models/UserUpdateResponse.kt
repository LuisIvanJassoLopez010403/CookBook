package com.example.cookbook.presentation.user.models

data class UserUpdateResponse(
    val message: String,
    val user: UserDetailsResponse
)