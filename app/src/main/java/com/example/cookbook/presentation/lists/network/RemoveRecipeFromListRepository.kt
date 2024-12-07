package com.example.cookbook.presentation.lists.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.lists.models.RemoveRecipeFromListBody
import com.example.cookbook.presentation.lists.models.RemoveRecipeFromListResponse

object RemoveRecipeFromListRepository {
    suspend fun removeRecipeFromList(listId: String, recipeId: String, token: String): RemoveRecipeFromListResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.removeRecipeFromList(RemoveRecipeFromListBody(listId, recipeId))
    }
}