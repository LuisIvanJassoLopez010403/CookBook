package com.example.cookbook.presentation.home.home.network

import com.example.cookbook.CategoryRepository
import com.example.cookbook.network.ApiService
import com.example.cookbook.network.RetrofitClientInstance
import com.example.cookbook.presentation.home.home.models.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.cookbook.presentation.home.home.models.HomeRecipeBody

object HomeRepository {

    suspend fun recipesbyCategory(token: String): List<HomeResponse> = withContext(Dispatchers.IO) {
        val apiService = RetrofitClientInstance.getRetrofitInstance(token = token).create(ApiService::class.java)
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