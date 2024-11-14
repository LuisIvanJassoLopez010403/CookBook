package com.example.cookbook.presentation.signup.network

import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.signup.models.SignupBody
import com.example.cookbook.presentation.signup.models.SignupResponse

object SignupBodyRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun doSignup(user: SignupBody): SignupResponse {
        return apiService.signup(user)
    }
}