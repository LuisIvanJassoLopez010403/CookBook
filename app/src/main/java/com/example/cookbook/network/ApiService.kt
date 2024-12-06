package com.example.cookbook.network

import com.example.cookbook.presentation.finder.models.SearchBody
import com.example.cookbook.presentation.finder.models.SearchResponse
import com.example.cookbook.Category
import com.example.cookbook.IngredientDetails
import com.example.cookbook.presentation.addrecipe.models.RecipeBody
import com.example.cookbook.presentation.addrecipe.models.RecipeResponse
import com.example.cookbook.presentation.finder.models.IngredientResponse
import com.example.cookbook.presentation.home.home.models.HomeResponse
import com.example.cookbook.presentation.lists.models.CreateListBody
import com.example.cookbook.presentation.lists.models.CreateListResponse
import com.example.cookbook.presentation.lists.models.UpdateListBody
import com.example.cookbook.presentation.lists.models.UpdateListResponse
import com.example.cookbook.presentation.login.models.LoginBody
import com.example.cookbook.presentation.login.models.LoginResponse
import com.example.cookbook.presentation.recipe.models.GetRecipeBody
import com.example.cookbook.presentation.recipe.models.GetRecipeResponse
import com.example.cookbook.presentation.signup.models.SignupBody
import com.example.cookbook.presentation.signup.models.SignupResponse
import com.example.cookbook.presentation.user.models.HistoryBody
import com.example.cookbook.presentation.user.models.HistoryItem
import com.example.cookbook.presentation.user.models.RecipeStructure
import com.example.cookbook.presentation.user.models.UpdateUserBody
import com.example.cookbook.presentation.user.models.UserDetailsBody
import com.example.cookbook.presentation.user.models.UserDetailsResponse
import com.example.cookbook.presentation.user.models.UserRecipesBody
import com.example.cookbook.presentation.user.models.UserUpdateResponse
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
private const val END_URL_GET_USER_RECIPES = "cookbook/get-user-recipes"
private const val END_URL_GET_USER = "cookbook/get-user"
private const val END_URL_UPDATE_USER = "cookbook/update-user"
private const val END_URL_GET_ALL_INGREDIENTS_BY_CATEGORY = "cookbook/get-all-ingredients-by-category"
private const val END_URL_GET_ALL_RECIPES_BY_CATEGORY = "cookbook/get-all-recipes-by-category"
private const val END_URL_GET_RECIPE = "cookbook/get-recipe"
private const val END_URL_CREATE_LIST = "cookbook/create-list"
private const val END_URL_UPDATE_LIST = "cookbook/update-list"

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

    @POST(END_URL_GET_USER_RECIPES)
    suspend fun getUserRecipes(@Body userRecipesBody: UserRecipesBody): List<RecipeStructure>

    @POST(END_URL_GET_USER)
    suspend fun getUser(@Body userDetailsBody: UserDetailsBody): UserDetailsResponse

    @POST(END_URL_UPDATE_USER)
    suspend fun updateUser(@Body updateUserBody: UpdateUserBody): UserUpdateResponse

    @GET(END_URL_GET_ALL_INGREDIENTS_BY_CATEGORY)
    suspend fun getIngredientsbycategory(): List<IngredientResponse>

    @GET(END_URL_GET_ALL_RECIPES_BY_CATEGORY)
    suspend fun getRecipebyCategory(): List<HomeResponse>

    @POST(END_URL_GET_RECIPE)
    suspend fun getRecipe(@Body getRecipeBody: GetRecipeBody): GetRecipeResponse

    @POST(END_URL_CREATE_LIST)
    suspend fun createList(@Body createListBody: CreateListBody): CreateListResponse

    @POST(END_URL_UPDATE_LIST)
    suspend fun updateList(@Body updateListBody: UpdateListBody): UpdateListResponse
}