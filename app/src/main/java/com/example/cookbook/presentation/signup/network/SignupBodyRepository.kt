package com.example.cookbook

object SignupBodyRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun doSignup(user: SignupBody): SignupResponse {
        return apiService.signup(user)
    }
}