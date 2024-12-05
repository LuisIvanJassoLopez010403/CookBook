package com.example.cookbook.presentation.finder.network

import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.finder.models.IngredientResponse
import com.example.cookbook.presentation.finder.models.SearchIngredient
import com.example.cookbook.presentation.home.home.models.HomeRecipeBody
import com.example.cookbook.presentation.home.home.models.HomeResponse
import com.example.cookbook.presentation.home.home.network.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IngredientByCategory{
    val apiService = RetrofitClientInstance.apiService

    suspend fun ingredientsbyCategory(): List<IngredientResponse> = withContext(Dispatchers.IO) {
        val apiResponse = apiService.getIngredientsbycategory()
        apiResponse.map { responseItem ->
            IngredientResponse(
                category = responseItem.category ?: "Unknown",
                ingredients = responseItem.ingredients.map { recipeItem ->
                    SearchIngredient(
                        _id = recipeItem._id,
                        nameIngredient = recipeItem.nameIngredient,
                        icon = recipeItem.icon ?: "Unknown"
                    )
                }
            )
        }
    }
}