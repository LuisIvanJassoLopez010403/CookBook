package com.example.cookbook.presentation.user.viewmodels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.preferences.getToken
import com.example.cookbook.preferences.getUserIdFromToken
import com.example.cookbook.presentation.user.models.UpdateUserBody
import com.example.cookbook.presentation.user.network.UpdateUserBodyRepository
import com.example.cookbook.utils.uriToBase64
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UpdateUserViewModel(
    private val updateUserBodyRepository: UpdateUserBodyRepository,
    private val appContext: Context
) : ViewModel() {

    var userId by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var updateMessage by mutableStateOf<String?>(null)

    // Campos editables
    var bio by mutableStateOf("")
    var profilePicture by mutableStateOf("")

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

    fun updateUserDetails() {
        viewModelScope.launch {
            val token = getToken(appContext).firstOrNull()
            if (token.isNullOrEmpty()) {
                errorMessage = "Authentication token is missing."
                return@launch
            }

            println("Profile Picture Base64 before API call: $profilePicture")

            val updateUserBody = UpdateUserBody(
                userId = userId,
                bio = bio,
                profile_picture = profilePicture
            )

            isLoading = true
            try {
                val response = updateUserBodyRepository.updateUser(updateUserBody, token)
                updateMessage = response.message
                errorMessage = null

                bio = response.user.bio
                profilePicture = response.user.profile_picture
            } catch (e: Exception) {
                errorMessage = "Failed to update user details: ${e.message}"
                updateMessage = null
            } finally {
                isLoading = false
            }
        }
    }

}

class UpdateUserViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateUserViewModel::class.java)) {
            return UpdateUserViewModel(UpdateUserBodyRepository, appContext) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
