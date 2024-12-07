package com.example.cookbook.presentation.lists.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.preferences.getToken
import com.example.cookbook.presentation.lists.network.RemoveRecipeFromListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class RemoveRecipeFromListViewModel(
    private val removeRecipeFromListRepository: RemoveRecipeFromListRepository,
    private val appContext: Context
) : ViewModel() {
    private val _responseMessage = MutableStateFlow<String?>(null)
    val responseMessage: StateFlow<String?> = _responseMessage

    var isLoading = MutableStateFlow(false)
    var errorMessage = MutableStateFlow<String?>(null)

    fun removeRecipeFromList(listId: String, recipeId: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val token = getToken(appContext).firstOrNull() ?: ""
                val response = removeRecipeFromListRepository.removeRecipeFromList(listId, recipeId, token)
                _responseMessage.value = response.message
                errorMessage.value = null
                onSuccess() // Ejecutar el callback
            } catch (e: Exception) {
                errorMessage.value = "Error al eliminar la receta: ${e.message}"
                _responseMessage.value = null
            } finally {
                isLoading.value = false
            }
        }
    }
}

class RemoveRecipeFromListViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RemoveRecipeFromListViewModel::class.java)) {
            return RemoveRecipeFromListViewModel(RemoveRecipeFromListRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}