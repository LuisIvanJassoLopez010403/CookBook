package com.example.cookbook.presentation.recipe.viewmodels

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.presentation.recipe.models.GetRecipeBody
import com.example.cookbook.presentation.recipe.models.GetRecipeResponse
import com.example.cookbook.preferences.getToken
import com.example.cookbook.preferences.getUserIdFromToken
import com.example.cookbook.presentation.recipe.network.GetRecipeBodyRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class GetRecipeViewModel(
    private val getRecipeBodyRepository: GetRecipeBodyRepository,
    private val appContext: Context
) : ViewModel() {

    var recipe by mutableStateOf<GetRecipeResponse?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    var userId by mutableStateOf("")

    init {
        loadUserId()
    }

    private fun loadUserId() {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            userId = if (!token.isNullOrEmpty()) {
                getUserIdFromToken(token) ?: ""
            } else {
                ""
            }
        }
    }

    fun getRecipe(recipeId: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = ""
            try {
                val token = getToken(appContext).firstOrNull()
                if (!token.isNullOrEmpty()) {
                    val response = getRecipeBodyRepository.getRecipe(
                        getRecipeBody = GetRecipeBody(id = recipeId, userId = userId),
                        token = token
                    )
                    recipe = response
                } else {
                    errorMessage = "Token not found"
                }
            } catch (e: Exception) {
                errorMessage = "Error fetching recipe: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}


class GetRecipeViewModelFactory(
    private val getRecipeBodyRepository: GetRecipeBodyRepository,
    private val appContext: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetRecipeViewModel::class.java)) {
            return GetRecipeViewModel(getRecipeBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
