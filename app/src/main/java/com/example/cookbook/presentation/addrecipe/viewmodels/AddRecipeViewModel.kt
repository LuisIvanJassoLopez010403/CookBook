package com.example.cookbook.presentation.addrecipe.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.Category
import com.example.cookbook.CategoryRepository
import com.example.cookbook.Ingredient
import com.example.cookbook.IngredientRepository
import com.example.cookbook.preferences.getToken
import com.example.cookbook.preferences.getUserIdFromToken
import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.addrecipe.models.RecipeResponse
import com.example.cookbook.presentation.addrecipe.network.RecipeBodyRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ViewModel
class AddRecipeViewModel(
    private val recipeBodyRepository: RecipeBodyRepository,
    private val appContext: Context
) : ViewModel() {
    var recipeResponse by mutableStateOf(RecipeResponse("", false))
    var state by mutableStateOf(0)

    var categories by mutableStateOf(emptyList<Pair<String, String>>())
    var selectedCategoryId by mutableStateOf("")

    var ingredients by mutableStateOf(emptyList<Pair<String, String>>())
    var SelectedIngredientId by mutableStateOf("")
    var selectedIngredientDetails by mutableStateOf<List<Ingredient>>(emptyList())

    var userId by mutableStateOf("")

    init {
        loadCategories()
        loadIngredients()
        loadUserId()
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

    private fun loadIngredients() {
        viewModelScope.launch {
            try {
                ingredients = IngredientRepository.getIngredients()
            } catch (exception: Exception) {
                ingredients = emptyList()
            }
        }
    }
    fun updateSelectedIngredients(selected: List<Ingredient>) {
        selectedIngredientDetails = selected
        SelectedIngredientId = selected.joinToString(",") { it._idIngredient._id }
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

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun createRecipe(
        nameRecipe: String,
        description: String,
        preptime: Number,
        ingredients: List<Ingredient>,
        steps: String,
        category: String,
        image: String,
        video: String,
        grade: Double
    ) {
        viewModelScope.launch {
            try {
                if (userId.isEmpty()) {
                    throw IllegalStateException("El ID del usuario no está disponible.")
                }

                val createdDate = getCurrentDate()
                recipeResponse = recipeBodyRepository.createRecipe(
                    RecipeBody(
                        nameRecipe,
                        description,
                        preptime,
                        ingredients,
                        steps,
                        createdDate,
                        category,
                        userId,
                        image,
                        video,
                        grade
                    )
                )
                state = 1
                recipeResponse.message = "Receta creada de forma exitosa"
                recipeResponse.isSuccess = true
            } catch (exception: Exception) {
                state = 1
                recipeResponse.message = "Error en la creación de la receta: ${exception.message}"
                recipeResponse.isSuccess = false
            }
        }
    }

}

class AddRecipeViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRecipeViewModel::class.java)) {
            return AddRecipeViewModel(RecipeBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
