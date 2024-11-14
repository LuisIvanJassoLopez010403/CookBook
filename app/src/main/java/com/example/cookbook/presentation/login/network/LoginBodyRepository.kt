package com.example.cookbook.presentation.login.network

import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.login.models.LoginBody
import com.example.cookbook.presentation.login.models.LoginResponse

object LoginBodyRepository {

    val apiService = RetrofitClientInstance.apiService

    suspend fun doLogin(user: LoginBody): LoginResponse {
        return apiService.login(user)
    }
}