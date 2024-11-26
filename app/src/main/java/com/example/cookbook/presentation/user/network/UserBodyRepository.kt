package com.example.cookbook.presentation.user.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.user.models.RecipeStructure
import com.example.cookbook.presentation.user.models.UserRecipesBody

object UserBodyRepository {
    suspend fun getUserRecipes(userRecipesBody: UserRecipesBody, token: String): List<RecipeStructure> {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.getUserRecipes(userRecipesBody)
    }
}