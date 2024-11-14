package com.example.cookbook


import com.example.cookbook.presentation.login.models.LoginBody
import com.example.cookbook.presentation.login.models.LoginResponse
import com.example.cookbook.presentation.signup.models.SignupBody
import com.example.cookbook.presentation.signup.models.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

private const val END_URL_LOGIN = "cookbook/login"
private const val END_URL_SIGNUP = "cookbook/signup"
private const val END_URL_CREATE_RECIPE = "cookbook/create-recipe"

interface ApiService {
    @POST(END_URL_LOGIN)
    suspend fun login(@Body user: LoginBody): LoginResponse

    @POST(END_URL_SIGNUP)
    suspend fun signup(@Body user: SignupBody): SignupResponse

    @POST(END_URL_CREATE_RECIPE)
    suspend fun createRecipe(@Body recipe: RecipeBody): RecipeResponse
}