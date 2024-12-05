package com.example.cookbook.presentation.recipe.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.recipe.models.GetRecipeResponse
import com.example.cookbook.presentation.recipe.network.GetRecipeBodyRepository
import com.example.cookbook.presentation.recipe.viewmodels.GetRecipeViewModel
import com.example.cookbook.presentation.recipe.viewmodels.GetRecipeViewModelFactory

@Composable
fun RecipeDetailView(recipeId: String, navController: NavController) {
    val appContext = LocalContext.current.applicationContext
    val viewModel: GetRecipeViewModel = viewModel(
        factory = GetRecipeViewModelFactory(GetRecipeBodyRepository, appContext)
    )
    LaunchedEffect(Unit) {
        viewModel.getRecipe(recipeId)
    }

    val isLoading = viewModel.isLoading
    val recipe = viewModel.recipe
    val errorMessage = viewModel.errorMessage

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        errorMessage.isNotEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage, color = Color.Red)
            }
        }
        recipe != null -> {
            RecipeDetails(recipe = recipe, navController = navController)
        }
    }
}

@Composable
fun RecipeDetails(recipe: GetRecipeResponse, navController: NavController) {
    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = { navController.navigate(Routes.UserView) }) {
                        Text(
                            text = "< Back",
                            fontSize = 18.sp,
                            color = Color(0xFFFFA500),
                            textAlign = TextAlign.Start
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    item {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = recipe.image.ifEmpty { "https://via.placeholder.com/150" }
                            ),
                            contentDescription = recipe.nameRecipe,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Nombre de la receta
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = recipe.nameRecipe,
                                style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                                color = androidx.compose.material3.MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Categoría
                        Box(
                            modifier = Modifier
                                .padding(top = 8.dp, bottom = 16.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    androidx.compose.material3.MaterialTheme.colorScheme.secondary.copy(
                                        alpha = 0.2f
                                    )
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = recipe.category?.category ?: "Sin categoría",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )
                        }

                        // Tiempo de preparación
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Alarm,
                                contentDescription = "Reloj",
                                modifier = Modifier.size(20.dp),
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Tiempo de preparación:",
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )
                        }
                        Text(
                            text = "${recipe.preptime} minutos",
                            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Descripción
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Description,
                                contentDescription = "Descripcion",
                                modifier = Modifier.size(20.dp),
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Descripción:",
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )
                        }
                        Text(
                            text = recipe.description,
                            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Ingredientes
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.FormatListNumbered,
                                contentDescription = "Ingredientes",
                                modifier = Modifier.size(20.dp),
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Ingredientes:",
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        recipe.ingredients.forEach { ingredient ->
                            Text(
                                text = "- ${ingredient.amount} ${ingredient.unit} de ${ingredient._idIngredient.nameIngredient}",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Pasos
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Checklist,
                                contentDescription = "Pasos",
                                modifier = Modifier.size(20.dp),
                                tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Pasos:",
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary
                            )
                        }
                        Text(
                            text = recipe.steps,
                            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomNavBarView(navController = navController)
        }
    )
}
