package com.example.cookbook.presentation.signup.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cookbook.presentation.signup.models.SignupBody
import com.example.cookbook.presentation.signup.network.SignupBodyRepository
import com.example.cookbook.presentation.signup.models.SignupResponse
import kotlinx.coroutines.launch

class SignupViewModel (val signupBodyRepository: SignupBodyRepository) : ViewModel() {
    var signupResponse: SignupResponse by mutableStateOf(SignupResponse("",false))
    var state: Int by mutableStateOf(0)

    fun doSignup(email: String, username: String, password: String, birthdate: String, gender: String){
        viewModelScope.launch {
            try {
                signupResponse = signupBodyRepository.doSignup(SignupBody(email, username, password, birthdate, gender))
                state = 1
                signupResponse.message = "Signup exitoso"
                signupResponse.isSuccess = true
            }
            catch (exception: Exception){
                state = 1
                signupResponse.message = "Error en el signup"
                signupResponse.isSuccess = false
            }
        }
    }
}

class SignupViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(SignupBodyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}