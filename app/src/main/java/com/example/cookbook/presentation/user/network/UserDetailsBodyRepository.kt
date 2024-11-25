package com.example.cookbook.presentation.user.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.user.models.UserDetailsBody
import com.example.cookbook.presentation.user.models.UserDetailsResponse

object UserDetailsBodyRepository {
    suspend fun getUser(userDetailsBody: UserDetailsBody, token: String): UserDetailsResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.getUser(userDetailsBody)
    }
}