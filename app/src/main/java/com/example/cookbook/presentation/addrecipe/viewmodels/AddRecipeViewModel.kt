package com.example.cookbook.presentation.addrecipe.viewmodels
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.Ingredient
import com.example.cookbook.RecipeBody
import com.example.cookbook.RecipeBodyRepository
import kotlinx.coroutines.launch
import java.util.Date

class RecipeViewModel(private val repository: RecipeBodyRepository) : ViewModel() {

    // Variables para almacenar los datos de la receta
    var nameRecipe = mutableStateOf("")
    var preptime = mutableStateOf("")
    var ingredients = mutableStateOf(listOf<Ingredient>())
    var steps = mutableStateOf(listOf<String>())
    var image = mutableStateOf("")
    var video = mutableStateOf("")
    var category = mutableStateOf("")
    var autor = mutableStateOf("")
    var calificacion = mutableStateOf(0.0)
    var fecha = mutableStateOf(Date())

    // Estado de la respuesta de creación
    var isRecipeCreated = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    // Función para agregar un ingrediente
    fun addIngredient(ingredient: Ingredient) {
        ingredients.value = ingredients.value + ingredient
    }

    // Función para agregar un paso
    fun addStep(step: String) {
        steps.value = steps.value + step
    }

    // Función para crear la receta
    fun createRecipe() {
        val newRecipe = RecipeBody(
            nameRecipe = nameRecipe.value,
            preptime = preptime.value,
            ingredients = ingredients.value,
            steps = steps.value,
            image = image.value,
            video = video.value,
            category = category.value,
            autor = autor.value,
            calificacion = calificacion.value,
            fecha = fecha.value
        )

        viewModelScope.launch {
            val success = repository.createRecipe(newRecipe).isSuccess
            if (success) {
                isRecipeCreated.value = true
            } else {
                errorMessage.value = "Error al crear la receta"
            }
        }
    }
}
