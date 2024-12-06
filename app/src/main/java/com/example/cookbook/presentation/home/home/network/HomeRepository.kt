package com.example.cookbook.presentation.home.home.network

import com.example.cookbook.CategoryRepository
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.home.home.models.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.cookbook.presentation.home.home.models.HomeRecipeBody

object HomeRepository {
    val apiService = RetrofitClientInstance.apiService

    suspend fun recipesbyCategory(): List<HomeResponse> = withContext(Dispatchers.IO) {
        val apiResponse = apiService.getRecipebyCategory()
        apiResponse.map { responseItem ->
            HomeResponse(
                category = responseItem.category ?: "Unknown",
                recipes = responseItem.recipes.map { recipeItem ->
                    HomeRecipeBody(
                        _id = recipeItem._id,
                        nameRecipe = recipeItem.nameRecipe,
                        description = recipeItem.description,
                        preptime = recipeItem.preptime,
                        ingredients = recipeItem.ingredients,
                        steps = recipeItem.steps,
                        createdDate = recipeItem.createdDate,
                        category = recipeItem.category,
                        autor = recipeItem.autor,
                        image = recipeItem.image ?: "",
                        video = recipeItem.video ?: "",
                        grade = recipeItem.grade ?: 0.0
                    )
                }
            )
        }
    }
}