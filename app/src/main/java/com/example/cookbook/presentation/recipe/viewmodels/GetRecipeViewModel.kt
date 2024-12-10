package com.example.cookbook.presentation.recipe.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.preferences.getRollFromToken
import com.example.cookbook.presentation.recipe.models.GetRecipeBody
import com.example.cookbook.presentation.recipe.models.GetRecipeResponse
import com.example.cookbook.preferences.getToken
import com.example.cookbook.preferences.getUserIdFromToken
import com.example.cookbook.presentation.recipe.models.DeleteRecipeBody
import com.example.cookbook.presentation.recipe.network.GetRecipeBodyRepository
import com.example.cookbook.presentation.user.models.UserDetailsBody
import com.example.cookbook.presentation.user.network.UserDetailsBodyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class GetRecipeViewModel(
    private val getRecipeBodyRepository: GetRecipeBodyRepository,
    private val appContext: Context
) : ViewModel() {


    var recipe by mutableStateOf<GetRecipeResponse?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf("")
        private set

    private var _userRole = MutableStateFlow("")
    val userRole: StateFlow<String> get() = _userRole

    var userId by mutableStateOf("")

    init {
        loadUserId()
        loadRoll()
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

    private fun loadRoll() {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            val role = if (!token.isNullOrEmpty()) {
                getRollFromToken(token) ?: ""
            } else {
                ""
            }
            _userRole.value = role
        }
    }

    fun getRecipe(recipeId: String) {
        viewModelScope.launch {
            isLoading = true
            message = ""
            try {
                val token = getToken(appContext).firstOrNull()
                if (!token.isNullOrEmpty()) {
                    val response = getRecipeBodyRepository.getRecipe(
                        getRecipeBody = GetRecipeBody(id = recipeId, userId = userId),
                        token = token
                    )
                    recipe = response
                } else {
                    message = "Token not found"
                }
            } catch (e: Exception) {
                message = "Error fetching recipe: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteRecipe(recipeId: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            message = ""
            try {
                val token = getToken(appContext).firstOrNull()
                if (!token.isNullOrEmpty()) {
                    val response = getRecipeBodyRepository.DeleteRecipe(
                        DeleteRecipeBody(recipeId),
                        token
                    )
                    if (response.message.contains("eliminada exitosamente", ignoreCase = true)) {
                        onSuccess()
                    } else {
                        message = "Error deleting recipe: ${response.message}"
                    }
                } else {
                    message = "Token not found"
                }
            } catch (e: Exception) {
                message = "Error deleting recipe: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

}






class GetRecipeViewModelFactory(
    private val getRecipeBodyRepository: GetRecipeBodyRepository,
    private val appContext: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GetRecipeViewModel::class.java)) {
            return GetRecipeViewModel(getRecipeBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
