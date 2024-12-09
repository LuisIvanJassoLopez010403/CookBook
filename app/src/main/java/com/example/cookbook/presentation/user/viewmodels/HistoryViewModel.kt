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
import com.example.cookbook.presentation.user.models.HistoryBody
import com.example.cookbook.presentation.user.models.RecipeHistory
import com.example.cookbook.presentation.user.network.HistoryBodyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyBodyRepository: HistoryBodyRepository,
    private val appContext: Context
) : ViewModel() {
    private val _recipeHistory = MutableStateFlow<List<RecipeHistory>>(emptyList())
    val recipeHistory: StateFlow<List<RecipeHistory>> = _recipeHistory

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

    fun viewHistory() {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            if (token.isNullOrEmpty() || userId.isEmpty()) {
                errorMessage = "Usuario no autenticado"
                return@launch
            }

            val historyBody = HistoryBody(id = userId)
            isLoading = true
            try {
                val response = historyBodyRepository.viewHistory(historyBody, token)
                if (response.isNotEmpty()) {
                    _recipeHistory.value = response[0].recipeHistory
                    errorMessage = null
                } else {
                    _recipeHistory.value = emptyList()
                    errorMessage = "No hay historial disponible."
                }
            } catch (e: Exception) {
                errorMessage = "Error al cargar el historial: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}

class HistoryViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(HistoryBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}