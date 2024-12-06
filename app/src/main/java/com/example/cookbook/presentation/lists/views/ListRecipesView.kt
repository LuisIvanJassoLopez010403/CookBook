package com.example.cookbook.presentation.lists.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModel
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModelFactory
import com.example.cookbook.presentation.user.views.RecipeCard

@Composable
fun ListRecipesView(listId: String, navController: NavController) {
    // Inicializar appContext y UserListsViewModel con la fábrica personalizada
    val appContext = LocalContext.current.applicationContext
    val userListsViewModel: UserListsViewModel = viewModel(factory = UserListsViewModelFactory(appContext))

    val userLists = userListsViewModel.userLists.collectAsState().value
    val isLoading = userListsViewModel.isLoading
    val errorMessage = userListsViewModel.errorMessage

    // Buscar la lista seleccionada
    val selectedList = userLists.find { it._id == listId }

    Column(modifier = Modifier.fillMaxSize()) {
        // Botón de regreso
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            TextButton(onClick = { navController.popBackStack() }) {
                Text(
                    text = "< " + stringResource(id = R.string.Back),
                    fontSize = 18.sp,
                    color = Color(0xFFFFA500),
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                selectedList == null -> {
                    Text(
                        text = "Lista no encontrada",
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(selectedList.recipes) { recipe ->
                            RecipeCard(
                                name = recipe.nameRecipe,
                                description = recipe.description,
                                imageUrl = recipe.image,
                                preptime = recipe.preptime,
                                modifier = Modifier.clickable {
                                    navController.navigate("recipe_detail/${recipe._id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}