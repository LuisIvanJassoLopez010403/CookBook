package com.example.cookbook.presentation.login.models

data class LoginResponse(
    var message: String,
    var token: String,
    var isSuccess: Boolean
)


