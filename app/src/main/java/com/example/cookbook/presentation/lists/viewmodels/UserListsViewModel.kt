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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserListsViewModel(
    private val userListsBodyRepository: UserListsBodyRepository,
    private val appContext: Context
) : ViewModel() {
    private val _userLists = MutableStateFlow<List<UserListsResponse>>(emptyList())
    val userLists: StateFlow<List<UserListsResponse>> = _userLists

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId

    init {
        loadUserId()
    }

    private fun loadUserId() {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            _userId.value = if (!token.isNullOrEmpty()) {
                getUserIdFromToken(token) ?: ""
            } else {
                ""
            }
            if (_userId.value.isNotEmpty()) {
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
                val updatedLists = userListsBodyRepository.getUserLists(UserListsBody(userId.value), token)
                _userLists.value = updatedLists
            } catch (e: Exception) {
                errorMessage = "Error al recargar las listas: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun removeRecipeLocally(listId: String, recipeId: String) {
        val updatedLists = _userLists.value.map { list ->
            if (list._id == listId) {
                list.copy(recipes = list.recipes.filter { it._id != recipeId })
            } else {
                list
            }
        }
        _userLists.value = updatedLists
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
