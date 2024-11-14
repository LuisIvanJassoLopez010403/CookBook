package com.example.cookbook

import com.example.cookbook.network.RetrofitClientInstance

object RecipeBodyRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun createRecipe(recipe: RecipeBody): RecipeResponse {
        return apiService.createRecipe(recipe)
    }
}