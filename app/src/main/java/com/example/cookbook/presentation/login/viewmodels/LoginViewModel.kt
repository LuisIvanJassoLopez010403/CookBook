package com.example.cookbook.presentation.login.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.presentation.login.models.LoginBody
import com.example.cookbook.presentation.login.network.LoginBodyRepository
import com.example.cookbook.presentation.login.models.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel (val loginbodyRepository: LoginBodyRepository) : ViewModel() {
    var loginResponse: LoginResponse by mutableStateOf(LoginResponse("", "",false))
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)

    fun doLogin(username: String, password: String){
        isLoading = true
        viewModelScope.launch {
            try {
                loginResponse = loginbodyRepository.doLogin(LoginBody(username, password))
                state = 1
                loginResponse.message = "Login exitoso"
                loginResponse.isSuccess = true
                isLoading = false
            }
            catch (exception: Exception){
                state = 1
                loginResponse.message = "Error en el login"
                loginResponse.isSuccess = false
                isLoading = false
            }
        }
    }
}

class LoginViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(LoginBodyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}