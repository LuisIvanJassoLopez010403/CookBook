package com.example.cookbook.presentation.finder.network

import com.example.cookbook.RecipeBody
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchResponse

object FinderBodyRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun searcRecipe(recipe: SearchBody): SearchResponse {
        return apiService.searchRecipe(recipe)
    }
}