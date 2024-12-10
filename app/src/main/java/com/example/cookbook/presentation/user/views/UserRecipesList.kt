package com.example.cookbook.presentation.user.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.presentation.user.viewmodels.UserRecipesViewModel
import com.google.android.gms.common.internal.StringResourceValueReader

@Composable
fun UserRecipesList(viewModel: UserRecipesViewModel = viewModel(), navController: NavController) {
    val userRecipes = viewModel.userRecipes.collectAsState()
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color(0xFFFFA500)
                )
            }/*
            errorMessage != null -> {
                Text(
                    text = errorMessage,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }*/
            userRecipes.value.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.NoRecipes),
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(userRecipes.value) { recipe ->
                        RecipeCard(
                            name = recipe.nameRecipe,
                            description = recipe.description,
                            imageUrl = recipe.image,
                            preptime = recipe.preptime,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate("recipe_detail/${recipe._id}")
                                }
                        )
                    }
                }
            }
        }
    }
}
