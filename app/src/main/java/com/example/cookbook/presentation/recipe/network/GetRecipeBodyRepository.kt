package com.example.cookbook.presentation.recipe.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.recipe.models.GetRecipeBody
import com.example.cookbook.presentation.recipe.models.GetRecipeResponse

object GetRecipeBodyRepository {
    suspend fun getRecipe(getRecipeBody: GetRecipeBody, token: String): GetRecipeResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.getRecipe(getRecipeBody)
    }

    /*suspend fun deleteRecipe(recipeId: String, token: String): Boolean {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token).create(ApiService::class.java)
        val response = apiService.deleteRecipe(mapOf( "id" to recipeId))
        return response.isSuccessful
    }*/
}