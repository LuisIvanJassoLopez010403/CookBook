package com.example.cookbook.presentation.finder.network

import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchResponse

object SpecifiedFinderRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun searchSpecifiedRecipe(recipe: SearchBody): SearchResponse {
        return apiService.searchSpecifiedRecipe(recipe)
    }
}