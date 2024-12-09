package com.example.cookbook.presentation.recipe.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchResponse
import com.example.cookbook.presentation.finder.network.FinderBodyRepository.apiService
import com.example.cookbook.presentation.recipe.models.DeleteRecipeBody
import com.example.cookbook.presentation.recipe.models.DeleteRecipeResponse
import com.example.cookbook.presentation.recipe.models.GetRecipeBody
import com.example.cookbook.presentation.recipe.models.GetRecipeResponse

object GetRecipeBodyRepository {
    suspend fun getRecipe(getRecipeBody: GetRecipeBody, token: String): GetRecipeResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.getRecipe(getRecipeBody)
    }

    suspend fun DeleteRecipe(deleteRecipeBody: DeleteRecipeBody, token: String): DeleteRecipeResponse {
        val apiService = RetrofitClientInstance
            .getRetrofitInstance(token = token)
            .create(ApiService::class.java)

        return apiService.deleteRecipe(deleteRecipeBody)
    }
}