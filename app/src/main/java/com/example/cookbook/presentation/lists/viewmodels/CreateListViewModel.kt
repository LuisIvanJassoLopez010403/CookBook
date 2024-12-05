package com.example.cookbook.presentation.lists.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.preferences.getToken
import com.example.cookbook.preferences.getUserIdFromToken
import com.example.cookbook.presentation.lists.models.CreateListBody
import com.example.cookbook.presentation.lists.models.CreateListResponse
import com.example.cookbook.presentation.lists.network.CreateListBodyRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CreateListViewModel(
    private val createListBodyRepository: CreateListBodyRepository,
    private val appContext: Context
) : ViewModel() {
    var createListResponse by mutableStateOf<CreateListResponse?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    var userId by mutableStateOf("")

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

    fun createList(nameList: String, recipes: List<String>, description: String = "", image: String = "") {
        if (nameList.isBlank() || recipes.isEmpty()) {
            errorMessage = "El nombre de la lista y las recetas son obligatorios."
            return
        }

        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            if (token.isNullOrEmpty()) {
                errorMessage = "Token de autenticación no disponible."
                return@launch
            }

            val createListBody = CreateListBody(
                nameList = nameList,
                recipes = recipes,
                autor = userId,
                description = description,
                image = image
            )

            isLoading = true
            try {
                val response = createListBodyRepository.createList(createListBody, token)
                createListResponse = response
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al crear la lista: ${e.message}"
                createListResponse = null
            } finally {
                isLoading = false
            }
        }
    }
}

class CreateListViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateListViewModel::class.java)) {
            return CreateListViewModel(CreateListBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}