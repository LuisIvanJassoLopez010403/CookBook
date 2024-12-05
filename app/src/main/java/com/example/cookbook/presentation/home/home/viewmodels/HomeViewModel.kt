package com.example.cookbook.presentation.home.home.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.preferences.getToken
import com.example.cookbook.preferences.getUserIdFromToken
import com.example.cookbook.presentation.home.home.models.HomeRecipeBody
import com.example.cookbook.presentation.home.home.models.HomeResponse
import com.example.cookbook.presentation.home.home.network.HomeRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HomeViewModel(val HomeBodyrepository: HomeRepository) : ViewModel() {
    var recipesbycategory by mutableStateOf(emptyList<HomeResponse>())
    val response = mutableStateOf<List<HomeResponse>>(emptyList())

    init {
        loadRecipesByCategory()
    }

    private fun loadRecipesByCategory() {
        viewModelScope.launch {
            try {
                val response = HomeBodyrepository.recipesbyCategory()
                recipesbycategory = response
                Log.d("HomeViewModel", "Recipes Loaded: $recipesbycategory")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading recipes", e)
                recipesbycategory = emptyList()
            }
        }
    }

    fun groupRecipesByCategory(response: List<HomeResponse>): List<HomeResponse> {
        return response.map { homeResponse ->
            HomeResponse(
                category = homeResponse.category,
                recipes = homeResponse.recipes
            )
        }
    }
}