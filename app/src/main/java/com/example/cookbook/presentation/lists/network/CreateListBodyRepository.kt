package com.example.cookbook.presentation.lists.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.lists.models.CreateListBody
import com.example.cookbook.presentation.lists.models.CreateListResponse

object CreateListBodyRepository {
    suspend fun createList(createListBody: CreateListBody, token: String): CreateListResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.createList(createListBody)
    }
}