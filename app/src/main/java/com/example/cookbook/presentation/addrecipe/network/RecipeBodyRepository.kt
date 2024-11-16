package com.example.cookbook.presentation.addrecipe.network

import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.addrecipe.models.RecipeResponse
import com.example.cookbook.network.RetrofitClientInstance

object RecipeBodyRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun createRecipe(recipe: RecipeBody): RecipeResponse {
        return apiService.createRecipe(recipe)
    }
}