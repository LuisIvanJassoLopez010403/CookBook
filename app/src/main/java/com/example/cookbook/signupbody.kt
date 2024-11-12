package com.example.cookbook

data class SignupBody(
    val email: String,
    val username: String,
    val password: String,
    val birthdate: String,
    val gender: String)