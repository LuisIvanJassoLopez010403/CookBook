package com.example.cookbook.presentation.addrecipe.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.CategoryRepository
import com.example.cookbook.Ingredient
import com.example.cookbook.IngredientDetails
import com.example.cookbook.IngredientRepository
import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.addrecipe.models.RecipeResponse
import com.example.cookbook.presentation.addrecipe.network.RecipeBodyRepository
import kotlinx.coroutines.launch
import java.util.Date

// ViewModel
class AddRecipeViewModel(private val recipeBodyRepository: RecipeBodyRepository) : ViewModel() {
    var recipeResponse by mutableStateOf(RecipeResponse("",false))
    var state by mutableStateOf(0)

    var categories by mutableStateOf(emptyList<Pair<String, String>>())
    var selectedCategoryId by mutableStateOf("")

    var ingredients by mutableStateOf(emptyList<Pair<String, String>>())
    var SelectedIngredientId by mutableStateOf("")

    init {
        loadCategories()
        loadIngredients()
    }

    // Campos para crear una receta
    var recipeName by mutableStateOf("")
    var description by mutableStateOf("")
    var preptime by mutableStateOf(0)
    var steps by mutableStateOf("")
    var selectedCategory by mutableStateOf("")
    //var ingredients by mutableStateOf(mutableListOf<Ingredient>())
    var image by mutableStateOf("")
    var video by mutableStateOf("")
    var calificacion by mutableStateOf(0.0)

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

    //fun addIngredient(id: String, unit: String, amount: Double) {
       // ingredients.add(Ingredient(_idIngredient = id, unit = unit, amount = amount))
    //}




    //fun removeIngredient(index: Int) {
        //if (index in ingredients.indices) {
            //ingredients.removeAt(index)
        //}
   // }

   /* fun createRecipe() {
        val recipeBody = RecipeBody(
            nameRecipe = recipeName,
            preptime = "$preptime minutos",
            ingredients = ingredients,
            steps = steps.split("\n"),
            image = image,
            video = video,
            category = selectedCategory,
            autor = "60d21b4967d0d8992e610c8a",
            calificacion = calificacion,
            fecha = Date()
        )

        viewModelScope.launch {
            try {
                recipeResponse = recipeBodyRepository.createRecipe(recipeBody)
                state = 1
            } catch (exception: Exception) {
                state = 1
                recipeResponse = RecipeResponse(
                    message = "Error creating recipe: ${exception.localizedMessage}",
                    isSuccess = false
                )
            }
        }
    }*/
}

class AddRecipeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRecipeViewModel::class.java)) {
            return AddRecipeViewModel(RecipeBodyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
