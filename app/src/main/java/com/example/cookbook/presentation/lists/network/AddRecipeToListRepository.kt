package com.example.cookbook.presentation.lists.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.lists.models.AddRecipeToListBody
import com.example.cookbook.presentation.lists.models.AddRecipeToListResponse

object AddRecipeToListRepository {
    suspend fun addRecipeToList(listId: String, recipeId: String, token: String): AddRecipeToListResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.addRecipeToList(AddRecipeToListBody(listId, recipeId))
    }
}