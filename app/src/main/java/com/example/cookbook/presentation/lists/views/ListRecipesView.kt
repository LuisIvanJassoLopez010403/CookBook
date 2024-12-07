package com.example.cookbook.presentation.lists.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.lists.viewmodels.RemoveRecipeFromListViewModel
import com.example.cookbook.presentation.lists.viewmodels.RemoveRecipeFromListViewModelFactory
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModel
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModelFactory
import com.example.cookbook.presentation.user.views.RecipeCard

@Composable
fun ListRecipesView(listId: String, navController: NavController) {
    // Inicializar appContext y ViewModels
    val appContext = LocalContext.current.applicationContext
    val userListsViewModel: UserListsViewModel = viewModel(factory = UserListsViewModelFactory(appContext))
    val removeRecipeFromListViewModel: RemoveRecipeFromListViewModel =
        viewModel(factory = RemoveRecipeFromListViewModelFactory(appContext))

    val userLists by userListsViewModel.userLists.collectAsState()
    val isLoading = userListsViewModel.isLoading
    val errorMessage = userListsViewModel.errorMessage

    // Buscar la lista seleccionada
    val selectedList = userLists.find { it._id == listId }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header con botón de regreso y acciones
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón de regreso
            TextButton(onClick = { navController.popBackStack() }) {
                Text(
                    text = "< " + stringResource(id = R.string.Back),
                    fontSize = 18.sp,
                    color = Color(0xFFFFA500),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Mostrar la lista de recetas o mensajes de carga/error
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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Tarjeta de receta
                                RecipeCard(
                                    name = recipe.nameRecipe,
                                    description = recipe.description,
                                    imageUrl = recipe.image,
                                    preptime = recipe.preptime,
                                    modifier = Modifier.weight(1f)
                                )

                                val reloadTrigger = remember { mutableStateOf(false) }

                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Eliminar Receta",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable {
                                            removeRecipeFromListViewModel.removeRecipeFromList(
                                                listId = listId,
                                                recipeId = recipe._id
                                            ) {
                                                navController.navigate(Routes.UserView)
                                            }
                                        },
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }

        // Mensajes de éxito o error al eliminar receta
        val successMessage = removeRecipeFromListViewModel.responseMessage.collectAsState().value
        val removeErrorMessage = removeRecipeFromListViewModel.errorMessage.collectAsState().value

        if (successMessage != null) {
            Text(
                text = successMessage,
                color = Color.Green,
                modifier = Modifier.padding(16.dp)
            )
        } else if (removeErrorMessage != null) {
            Text(
                text = removeErrorMessage,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
