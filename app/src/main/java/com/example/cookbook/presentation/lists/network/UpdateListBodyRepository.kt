package com.example.cookbook.presentation.lists.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.lists.models.UpdateListBody
import com.example.cookbook.presentation.lists.models.UpdateListResponse

object UpdateListBodyRepository {
    suspend fun updateList(updateListBody: UpdateListBody, token: String): UpdateListResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.updateList(updateListBody)
    }
}