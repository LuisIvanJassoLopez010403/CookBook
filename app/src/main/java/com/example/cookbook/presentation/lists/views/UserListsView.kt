package com.example.cookbook.presentation.lists.views

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModel

@Composable
fun UserListsView(viewModel: UserListsViewModel = viewModel(), navController: NavController) {
    val userLists = viewModel.userLists.collectAsState()
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }
            userLists.value.isEmpty() -> {
                Text(
                    text = "No hay listas disponibles.",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(userLists.value) { list ->
                        ListCard(
                            name = list.nameList,
                            description = list.description.orEmpty(),
                            recipesCount = list.recipes.size,
                            onClick = {
                                navController.navigate("list_recipes/${list._id}")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
