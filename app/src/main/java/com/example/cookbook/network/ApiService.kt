package com.example.cookbook.network

import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchResponse
import com.example.cookbook.Category
import com.example.cookbook.IngredientDetails
import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.addrecipe.models.RecipeResponse
import com.example.cookbook.presentation.login.models.LoginBody
import com.example.cookbook.presentation.login.models.LoginResponse
import com.example.cookbook.presentation.signup.models.SignupBody
import com.example.cookbook.presentation.signup.models.SignupResponse
import com.example.cookbook.presentation.user.models.HistoryBody
import com.example.cookbook.presentation.user.models.HistoryItem
import com.example.cookbook.presentation.user.models.HistoryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val END_URL_LOGIN = "cookbook/login"
private const val END_URL_SIGNUP = "cookbook/signup"
private const val END_URL_CREATE_RECIPE = "cookbook/create-recipe"
private const val END_URL_SEARCH_RECIPE = "cookbook/search-recipe"
private const val END_URL_SEARCH_SPECIFIED_RECIPE = "cookbook/search-specified-recipe"
private const val END_URL_GET_ALL_CATEGORIES = "cookbook/get-all-categories"
private const val END_URL_GET_ALL_INGREDIENTS = "cookbook/get-all-ingredients"
private const val END_URL_VIEW_HISTORY = "cookbook/view-history"

interface ApiService {
    @POST(END_URL_LOGIN)
    suspend fun login(@Body user: LoginBody): LoginResponse

    @POST(END_URL_SIGNUP)
    suspend fun signup(@Body user: SignupBody): SignupResponse

    @POST(END_URL_CREATE_RECIPE)
    suspend fun createRecipe(@Body recipe: RecipeBody): RecipeResponse

    @POST(END_URL_SEARCH_RECIPE)
    suspend fun searchRecipe(@Body recipe: SearchBody): SearchResponse

    @POST(END_URL_SEARCH_SPECIFIED_RECIPE)
    suspend fun searchSpecifiedRecipe(@Body recipe: SearchBody): SearchResponse

    @GET(END_URL_GET_ALL_CATEGORIES)
    suspend fun getAllCategories(): List<Category>

    @GET(END_URL_GET_ALL_INGREDIENTS)
    suspend fun getAllIngredients(): List<IngredientDetails>

    @POST(END_URL_VIEW_HISTORY)
    suspend fun viewHistory(@Body history: HistoryBody): List<HistoryItem>
}