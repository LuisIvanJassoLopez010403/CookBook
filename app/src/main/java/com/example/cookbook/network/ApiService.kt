package com.example.cookbook.network


import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchResponse
import com.example.cookbook.Category
import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.addrecipe.models.RecipeResponse
import com.example.cookbook.presentation.login.models.LoginBody
import com.example.cookbook.presentation.login.models.LoginResponse
import com.example.cookbook.presentation.signup.models.SignupBody
import com.example.cookbook.presentation.signup.models.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private const val END_URL_LOGIN = "cookbook/login"
private const val END_URL_SIGNUP = "cookbook/signup"
private const val END_URL_CREATE_RECIPE = "cookbook/create-recipe"
private const val END_URL_SEARCH_RECIPE = "cookbook/search-recipe"
private const val END_URL_GET_ALL_CATEGORIES = "cookbook/get-all-categories"

interface ApiService {
    @POST(END_URL_LOGIN)
    suspend fun login(@Body user: LoginBody): LoginResponse

    @POST(END_URL_SIGNUP)
    suspend fun signup(@Body user: SignupBody): SignupResponse

    @POST(END_URL_CREATE_RECIPE)
    suspend fun createRecipe(@Body recipe: RecipeBody): RecipeResponse

    @POST(END_URL_SEARCH_RECIPE)
    suspend fun searchRecipe(@Body recipe: SearchBody): SearchResponse

    @GET(END_URL_GET_ALL_CATEGORIES)
    suspend fun getAllCategories(): List<Category>
}