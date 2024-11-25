package com.example.cookbook.presentation.user.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.preferences.getToken
import com.example.cookbook.preferences.getUserIdFromToken
import com.example.cookbook.presentation.user.models.UserRecipesBody
import com.example.cookbook.presentation.user.models.RecipeStructure
import com.example.cookbook.presentation.user.network.UserBodyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserRecipesViewModel(
    private val userBodyRepository: UserBodyRepository,
    private val appContext: Context
) : ViewModel() {
    private val _userRecipes = MutableStateFlow<List<RecipeStructure>>(emptyList())
    val userRecipes: StateFlow<List<RecipeStructure>> = _userRecipes

    var userId by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    init {
        loadUserId()
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

    fun getUserRecipes() {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            if (token.isNullOrEmpty() || userId.isEmpty()) {
                errorMessage = "Usuario no autenticado"
                return@launch
            }

            val userRecipesBody = UserRecipesBody(userId = userId)
            isLoading = true
            try {
                val response = userBodyRepository.getUserRecipes(userRecipesBody, token)
                _userRecipes.value = response
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al cargar las recetas del usuario: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}


class UserRecipesViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserRecipesViewModel::class.java)) {
            return UserRecipesViewModel(UserBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
