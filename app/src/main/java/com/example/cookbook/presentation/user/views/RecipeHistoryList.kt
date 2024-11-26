package com.example.cookbook.presentation.user.views

import androidx.compose.foundation.layout.*
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
import com.example.cookbook.presentation.user.viewmodels.HistoryViewModel

@Composable
fun RecipeHistoryList(viewModel: HistoryViewModel = viewModel()) {
    val recipeHistory = viewModel.recipeHistory.collectAsState()
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                // Indicador de carga
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            errorMessage != null -> {
                // Mensaje de error
                Text(
                    text = errorMessage,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Red
                )
            }
            recipeHistory.value.isEmpty() -> {
                // Mensaje de lista vacía
                Text(
                    text = "No tienes recetas en el historial",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
            else -> {
                // Lista de recetas
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(recipeHistory.value) { recipe ->
                        RecipeCard(
                            name = recipe.recipeId.nameRecipe,
                            description = recipe.recipeId.description,
                            imageUrl = recipe.recipeId.image,
                            preptime = recipe.recipeId.preptime,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
