package com.example.cookbook

import retrofit2.http.Body
import retrofit2.http.POST

private const val END_URL_LOGIN = "cookbook/login"
private const val END_URL_SIGNUP = "cookbook/signup"

interface ApiService {
    @POST(END_URL_LOGIN)
    suspend fun login(@Body user: LoginBody): LoginResponse

    @POST(END_URL_SIGNUP)
    suspend fun signup(@Body user: SignupBody): SignupResponse
}