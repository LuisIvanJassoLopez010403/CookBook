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
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchRecipeBody
import com.example.cookbook.presentation.finder.network.SpecifiedFinderRepository
import kotlinx.coroutines.launch

class SpecifiedFinderViewModel(val FinderBodyRepository: SpecifiedFinderRepository) : ViewModel() {
    var isLoading: Boolean by mutableStateOf(false)
    val searchResponse = mutableStateOf<List<SearchRecipeBody>>(emptyList())
    val searchQuery = mutableStateOf("")
    var categories by mutableStateOf(emptyList<Pair<String, String>>())
    var ingredients by mutableStateOf(emptyList<Pair<String, String>>())
    var selectedingredients by mutableStateOf(mutableSetOf<String>())
    var selectedcategories by mutableStateOf(mutableSetOf<String>())


    init {
        loadCategories()
        loadIngredients()
    }

    fun searchRecipes(nameRecipe: String) {

        if (nameRecipe.isBlank() && ingredients.isEmpty()) {
            return // Detén la función si no hay filtros aplicados
        }

        isLoading = true
        viewModelScope.launch {
            try {
                val searchBody = SearchBody(
                    nameRecipe = nameRecipe,
                    ingredients = selectedingredients.toList(),
                    category = selectedcategories.toList()
                )
                val response = FinderBodyRepository.searchSpecifiedRecipe(searchBody)
                println("Respuesta de la API: ${response.recipes}")
                searchResponse.value = response.recipes
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

    fun toggleCategoriesSelection(id: String){
        if (selectedcategories.contains(id)){
            selectedcategories.remove(id)
        }else{
            selectedcategories.add(id)
        }
    }

    fun getIngredientsList(): List<IngredientDetails> {
        return ingredients.map { (id,name) ->
            IngredientDetails(
                _id = id,
                nameIngredient = name,
                categoy = "",
                __v = 0
            )
        }
    }

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

    fun toggleIngredientSelection(id: String){
        if (selectedingredients.contains(id)){
            selectedingredients.remove(id)
        }else{
            selectedingredients.add(id)
        }
    }

}