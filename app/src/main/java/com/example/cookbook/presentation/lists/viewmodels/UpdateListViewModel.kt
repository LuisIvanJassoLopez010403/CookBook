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
import com.example.cookbook.presentation.lists.models.UpdateListBody
import com.example.cookbook.presentation.lists.models.UpdateListResponse
import com.example.cookbook.presentation.lists.network.UpdateListBodyRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UpdateListViewModel(
    private val updateListBodyRepository: UpdateListBodyRepository,
    private val appContext: Context
) : ViewModel() {
    var updateListResponse by mutableStateOf<UpdateListResponse?>(null)
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

    fun updateList(
        listId: String,
        recipeId: String
    ) {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            if (token.isNullOrEmpty()) {
                errorMessage = "Error: Usuario no autenticado."
                return@launch
            }

            val updateListBody = UpdateListBody(
                id = listId,
                nameList = "",
                image = "",
                description = "",
                recipes = listOf(recipeId)
            )

            isLoading = true
            try {
                val response = updateListBodyRepository.updateList(updateListBody, token)
                updateListResponse = response
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Error al actualizar la lista: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}


class UpdateListViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateListViewModel::class.java)) {
            return UpdateListViewModel(UpdateListBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}