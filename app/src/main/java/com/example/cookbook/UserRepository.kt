package com.example.cookbook

object UserRepository {

    val apiService = RetrofitClientInstance.apiService

    suspend fun doLogin(user: User): LoginResponse {
        return apiService.login(user)
    }
}