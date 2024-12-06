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
import com.example.cookbook.presentation.lists.models.UserListsBody
import com.example.cookbook.presentation.lists.models.UserListsResponse
import com.example.cookbook.presentation.lists.network.UserListsBodyRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserListsViewModel(
    private val userListsBodyRepository: UserListsBodyRepository,
    private val appContext: Context
) : ViewModel() {
    var userId by mutableStateOf("")
    var userLists by mutableStateOf<List<UserListsResponse>>(emptyList())
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

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
            if (userId.isNotEmpty()) {
                fetchUserLists()
            }
        }
    }

    fun fetchUserLists() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val token = getToken(appContext).firstOrNull() ?: ""
                userLists = userListsBodyRepository.getUserLists(UserListsBody(userId), token)
            } catch (e: Exception) {
                errorMessage = "Error al cargar las listas: ${e.message}"
                userLists = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
}

class UserListsViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListsViewModel::class.java)) {
            return UserListsViewModel(UserListsBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
