package com.example.cookbook.presentation.finder.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.Category
import com.example.cookbook.CategoryRepository
import com.example.cookbook.IngredientDetails
import com.example.cookbook.IngredientRepository
import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.network.FinderBodyRepository
import kotlinx.coroutines.launch

class FinderViewModel(val FinderBodyRepository: FinderBodyRepository) : ViewModel() {
    var isLoading: Boolean by mutableStateOf(false)
    val loginResponse = mutableStateOf<List<RecipeBody>>(emptyList())
    val searchQuery = mutableStateOf("")
    var categories by mutableStateOf(emptyList<Pair<String, String>>())
    var ingredients by mutableStateOf(emptyList<Pair<String, String>>())

    init {
        loadCategories()
        loadIngredients()
    }

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

    fun getCategoryList(): List<Category> {
        return categories.map { (id, name) ->
            Category(
                _id = id,
                categoria = name,
                category = null
            )
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                categories = CategoryRepository.getCategories()
            } catch (exception: Exception) {
                // Manejar errores (opcional)
                categories = emptyList()
            }
        }
    }

    /*fun getIngredientsList(): List<IngredientDetails> {
        return ingredients.map { (id,name, cat) ->
            IngredientDetails(
                _id = id,
                nameIngredient = name,
                categoy = cat,
                __v = null
            )
        }
    }*/

    private fun loadIngredients() {
        viewModelScope.launch {
            try {
                ingredients = IngredientRepository.getIngredients()
            } catch (exception: Exception) {
                // Manejar errores (opcional)
                ingredients = emptyList()
            }

        }
    }
}