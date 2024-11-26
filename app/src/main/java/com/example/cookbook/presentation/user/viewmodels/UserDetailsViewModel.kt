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
import com.example.cookbook.presentation.user.models.UserDetailsBody
import com.example.cookbook.presentation.user.network.UserDetailsBodyRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val userDetailsBodyRepository: UserDetailsBodyRepository,
    private val appContext: Context
) : ViewModel() {

    var userId by mutableStateOf("")
    var email by mutableStateOf("")
    var username by mutableStateOf("")
    var birthdate by mutableStateOf("")
    var gender by mutableStateOf("")
    var bio by mutableStateOf("This user has not added a bio yet.")
    var profilePicture by mutableStateOf("")
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

            if (userId.isNotEmpty()) {
                fetchUserDetails()
            }
        }
    }

    fun fetchUserDetails() {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            if (token.isNullOrEmpty()) {
                errorMessage = "Authentication token is missing."
                return@launch
            }

            val userDetailsBody = UserDetailsBody(userId = userId)
            isLoading = true
            try {
                val response = userDetailsBodyRepository.getUser(userDetailsBody, token)
                email = response.email
                username = response.username
                birthdate = response.birthdate
                gender = response.gender
                bio = response.bio.ifEmpty { "This user has not added a bio yet." }
                profilePicture = response.profile_picture.ifEmpty { "default_placeholder_url" }
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = "Failed to load user details: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}

class UserDetailsViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(UserDetailsBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
