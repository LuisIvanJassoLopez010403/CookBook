package com.example.cookbook

object LoginBodyRepository {

    val apiService = RetrofitClientInstance.apiService

    suspend fun doLogin(user: LoginBody): LoginResponse {
        return apiService.login(user)
    }
}