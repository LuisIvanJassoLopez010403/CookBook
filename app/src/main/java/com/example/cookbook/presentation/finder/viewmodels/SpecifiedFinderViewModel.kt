package com.example.cookbook.presentation.finder.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.Category
import com.example.cookbook.CategoryRepository
import com.example.cookbook.IngredientDetails
import com.example.cookbook.IngredientRepository
import com.example.cookbook.presentation.finder.models.IngredientResponse
import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchIngredient
import com.example.cookbook.presentation.finder.models.SearchRecipeBody
import com.example.cookbook.presentation.finder.network.IngredientByCategory
import com.example.cookbook.presentation.finder.network.SpecifiedFinderRepository
import com.example.cookbook.presentation.home.home.models.HomeResponse
import com.example.cookbook.presentation.home.home.network.HomeRepository
import kotlinx.coroutines.launch

class SpecifiedFinderViewModel(val FinderBodyRepository: SpecifiedFinderRepository,
                               val IngredientBody: IngredientByCategory
) : ViewModel() {
    val searchResponse = mutableStateOf<List<SearchRecipeBody>>(emptyList())
    val searchQuery = mutableStateOf("")
    var categories by mutableStateOf(emptyList<Triple<String, String, String?>>())
    var ingredientsbycategory by mutableStateOf(emptyList<IngredientResponse>())
    var selectedingredients by mutableStateOf(mutableSetOf<String>())
    var selectedcategories by mutableStateOf(mutableSetOf<String>())
    var isLoading by mutableStateOf(false)
        private set


    init {
        loadCategories()
        loadIngredientsbyCategory()
    }

    fun searchRecipes(nameRecipe: String) {

        if (nameRecipe.isBlank() && ingredientsbycategory.isEmpty()) {
            return
        }


        viewModelScope.launch {
            isLoading = true
            try {
                val searchBody = SearchBody(
                    nameRecipe = nameRecipe,
                    ingredients = selectedingredients.toList(),
                    category = selectedcategories.toList()
                )
                val response = FinderBodyRepository.searchSpecifiedRecipe(searchBody)
                println("Respuesta de la API: ${response.recipes}")
                searchResponse.value = response.recipes
                isLoading = false
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading = false
            }
        }
    }

    fun getCategoryList(): List<Category> {
        return categories.map { (id, name, icon) ->
            Category(
                _id = id,
                categoria = name,
                category = null,
                icon = icon
            )
        }
    }

    fun loadCategories() {
        isLoading = true
        viewModelScope.launch {
            try {
                categories = CategoryRepository.getCategories()
                isLoading = false
            } catch (exception: Exception) {
                categories = emptyList()
                isLoading = false
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

    private fun loadIngredientsbyCategory() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = IngredientBody.ingredientsbyCategory()
                ingredientsbycategory = response
                Log.d("SpecifiedFinderViewModel", "Ingredients Loaded: $ingredientsbycategory")
                isLoading = false
            } catch (e: Exception) {
                Log.e("SpecifiedFinderViewModel", "Error loading ingredients", e)
                ingredientsbycategory = emptyList()
                isLoading = false
            }
        }
    }

    fun groupingredientsbycategory(response: List<IngredientResponse>): List<IngredientResponse> {
        return response.map { ingredientresponse ->
            IngredientResponse(
                category = ingredientresponse.category,
                ingredients = ingredientresponse.ingredients
            )
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