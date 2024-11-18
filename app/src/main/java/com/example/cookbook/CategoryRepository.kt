package com.example.cookbook

import com.example.cookbook.network.RetrofitClientInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CategoryRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun getCategories(): List<Pair<String, String>> = withContext(Dispatchers.IO) {
        val categories = apiService.getAllCategories()
        categories.map { category ->
            category._id to category.name
        }
    }
}
