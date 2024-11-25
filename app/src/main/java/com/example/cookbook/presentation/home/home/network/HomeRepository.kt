package com.example.cookbook.presentation.home.home.network

import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.home.home.models.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object HomeRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun recipesbyCategory(): List<HomeResponse>{
        return withContext(Dispatchers.IO){
            apiService.getRecipebyCategory()
        }
    }
}