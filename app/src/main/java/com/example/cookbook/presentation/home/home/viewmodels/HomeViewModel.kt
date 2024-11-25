package com.example.cookbook.presentation.home.home.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.Category
import com.example.cookbook.CategoryRepository
import com.example.cookbook.presentation.home.home.models.HomeResponse
import com.example.cookbook.presentation.home.home.network.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(val HomeBodyrepository: HomeRepository) : ViewModel() {
    var categories by mutableStateOf(emptyList<Pair<String, String>>())
    var recipesbycategory by mutableStateOf(emptyList<HomeResponse>())

    init {
        loadCategories()
        loadRecipesByCategory()
    }

    private fun loadRecipesByCategory() {
        viewModelScope.launch {
            try {
                // Llama al repositorio para obtener las recetas agrupadas
                recipesbycategory = HomeBodyrepository.recipesbyCategory()
            } catch (e: Exception) {
                // Maneja el error adecuadamente
                recipesbycategory = emptyList()
            }
        }
    }

    fun getRecipesByCategory(): List<HomeResponse> {
        return recipesbycategory
    }


    private fun loadCategories() {
        viewModelScope.launch {
            try {
                categories = CategoryRepository.getCategories()
            } catch (exception: Exception) {
                categories = emptyList()
            }
        }
    }

    fun getCategoryList(): List<Category> {
        return categories.map { (id, name) ->
            Category(
                _id = id,
                categoria = name,
                category = null
            )
        }
    }
}