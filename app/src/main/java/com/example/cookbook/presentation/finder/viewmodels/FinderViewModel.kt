package com.example.cookbook.presentation.finder.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.RecipeBody
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.network.FinderBodyRepository
import kotlinx.coroutines.launch

class FinderViewModel(val FinderBodyRepository: FinderBodyRepository) : ViewModel() {
    var isLoading: Boolean by mutableStateOf(false)
    val loginResponse = mutableStateOf<List<RecipeBody>>(emptyList())
    val searchQuery = mutableStateOf("")

    fun searchRecipes(nameRecipe: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                val searchBody = SearchBody(
                    nameRecipe = nameRecipe,
                    //ingredients = emptyList(),
                    //category =
                )
                val response = FinderBodyRepository.searcRecipe(searchBody)
                println("Respuesta de la API: ${response.recipes}")
                loginResponse.value = response.recipes
            } catch (e: Exception) {
                // Manejo de errores
                e.printStackTrace()
                isLoading = false
            }
        }
    }
}