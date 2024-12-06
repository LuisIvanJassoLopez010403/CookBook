package com.example.cookbook.presentation.lists.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.preferences.getToken
import com.example.cookbook.presentation.lists.network.AddRecipeToListRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class AddRecipeToListViewModel(
    private val addRecipeToListRepository: AddRecipeToListRepository,
    private val appContext: Context
) : ViewModel() {
    var isLoading by mutableStateOf(false)
    var successMessage by mutableStateOf<String?>(null)
    var errorMessage by mutableStateOf<String?>(null)

    fun addRecipeToList(listId: String, recipeId: String) {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            if (token.isNullOrEmpty()) {
                errorMessage = "Token no encontrado"
                return@launch
            }

            isLoading = true
            try {
                val response = addRecipeToListRepository.addRecipeToList(listId, recipeId, token)
                successMessage = response.message
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al agregar la receta: ${e.message}"
                successMessage = null
            } finally {
                isLoading = false
            }
        }
    }
}

class AddRecipeToListViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRecipeToListViewModel::class.java)) {
            return AddRecipeToListViewModel(AddRecipeToListRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
