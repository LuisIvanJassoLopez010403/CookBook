package com.example.cookbook

import com.example.cookbook.network.RetrofitClientInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IngredientRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun getIngredients(): List<Pair<String, String>> = withContext(Dispatchers.IO) {
        val ingredients = apiService.getAllIngredients()
        ingredients.map { ingredient ->
            ingredient._id to ingredient.nameIngredient
        }
    }
}