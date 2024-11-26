package com.example.cookbook.presentation.user.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.user.models.UpdateUserBody
import com.example.cookbook.presentation.user.models.UserUpdateResponse

object UpdateUserBodyRepository {
    suspend fun updateUser(updateUserBody: UpdateUserBody, token: String): UserUpdateResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.updateUser(updateUserBody)
    }
}