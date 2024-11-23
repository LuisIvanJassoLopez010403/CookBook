package com.example.cookbook.presentation.addrecipe.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.addrecipe.models.RecipeResponse
import com.example.cookbook.network.RetrofitClientInstance

object RecipeBodyRepository {
    suspend fun createRecipe(recipe: RecipeBody, token: String): RecipeResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.createRecipe(recipe)
    }
}
