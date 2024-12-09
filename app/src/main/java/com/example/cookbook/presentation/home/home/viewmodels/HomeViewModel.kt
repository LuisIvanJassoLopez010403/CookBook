package com.example.cookbook.presentation.home.home.viewmodels

import android.content.Context
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

class HomeViewModel(
    val HomeBodyrepository: HomeRepository,
    private val appContext: Context
    ) : ViewModel() {
    var recipesbycategory by mutableStateOf(emptyList<HomeResponse>())
    val response = mutableStateOf<List<HomeResponse>>(emptyList())
    var isLoading by mutableStateOf(false)
        private set

    init {
        loadRecipesByCategory()
    }

    private fun loadRecipesByCategory() {
        isLoading = true
        viewModelScope.launch {
            try {
                val token = getToken(appContext).firstOrNull().orEmpty()
                if (token.isNotBlank()) {
                val response = HomeBodyrepository.recipesbyCategory(token)
                recipesbycategory = response
                Log.d("HomeViewModel", "Recipes Loaded: $recipesbycategory")
                isLoading = false
                } else {
                    Log.e("HomeViewModel", "Token is empty")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading recipes", e)
                recipesbycategory = emptyList()
                isLoading = false
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