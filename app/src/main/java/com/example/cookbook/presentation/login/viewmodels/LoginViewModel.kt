package com.example.cookbook.presentation.login.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.LoginResponse
import com.example.cookbook.User
import com.example.cookbook.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel (val userRepository: UserRepository) : ViewModel() {
    var loginResponse: LoginResponse by mutableStateOf(LoginResponse("", ""))
    var isLoading: Boolean by mutableStateOf(false)
    var state: Int by mutableStateOf(0)

    fun doLogin(username: String, password: String){
        viewModelScope.launch {
            try {
                loginResponse = userRepository.doLogin(User(username, password))
                state = 1
                loginResponse.message = "Login exitoso"
                isLoading = false
            }
            catch (exception: Exception){
                state = 1
                loginResponse.message = "Error en el login"
                loginResponse.message = "ocurrio error"
                isLoading = false
            }
        }
    }
}

class LoginViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}