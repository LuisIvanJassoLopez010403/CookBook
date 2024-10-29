package com.example.cookbook

import retrofit2.http.Body
import retrofit2.http.POST

private const val END_URL_LOGIN_WITH_EMAIL = "cookbook/login"



interface ApiService {
    @POST(END_URL_LOGIN_WITH_EMAIL)
    suspend fun login(@Body user: User): LoginResponse
}