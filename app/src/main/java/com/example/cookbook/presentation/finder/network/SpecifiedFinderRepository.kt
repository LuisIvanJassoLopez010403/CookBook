package com.example.cookbook.presentation.finder.network

import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchResponse

object SpecifiedFinderRepository {

    suspend fun searchSpecifiedRecipe(recipe: SearchBody, token: String): SearchResponse {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
        return apiService.searchSpecifiedRecipe(recipe)
    }
}