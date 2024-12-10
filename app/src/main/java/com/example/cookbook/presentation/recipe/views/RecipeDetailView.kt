package com.example.cookbook.presentation.recipe.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.navigation.Routes
import com.example.cookbook.preferences.clearToken
import com.example.cookbook.presentation.lists.viewmodels.AddRecipeToListViewModel
import com.example.cookbook.presentation.lists.viewmodels.AddRecipeToListViewModelFactory
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModel
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModelFactory
import com.example.cookbook.presentation.recipe.models.GetRecipeResponse
import com.example.cookbook.presentation.recipe.network.GetRecipeBodyRepository
import com.example.cookbook.presentation.recipe.viewmodels.GetRecipeViewModel
import com.example.cookbook.presentation.recipe.viewmodels.GetRecipeViewModelFactory
import com.example.cookbook.presentation.recipe.viewmodels.SharedRecipeViewModel
import com.example.cookbook.utils.base64ToBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RecipeDetailView(recipeId: String, navController: NavController) {
    val appContext = LocalContext.current.applicationContext
    val viewModel: GetRecipeViewModel = viewModel(
        factory = GetRecipeViewModelFactory(GetRecipeBodyRepository, appContext)
    )

    val sharedRecipeViewModel: SharedRecipeViewModel = viewModel()
    LaunchedEffect(Unit) {
        viewModel.getRecipe(recipeId)
    }

    val isLoading = viewModel.isLoading
    val recipe = viewModel.recipe
    val errorMessage = viewModel.message
    val userRole by viewModel.userRole.collectAsState()

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color(0xFFFFA500)
                )
            }
        }

        errorMessage.isNotEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage, color = Color.Red)
            }
        }

        recipe != null -> {
            RecipeDetails(
                recipe = recipe,
                navController = navController,
                userRole = userRole,
                viewModel = viewModel,
                sharedRecipeViewModel = sharedRecipeViewModel
            )
        }
    }
}

@Composable
fun RecipeDetails(
    recipe: GetRecipeResponse,
    navController: NavController,
    userRole: String,
    viewModel: GetRecipeViewModel,
    sharedRecipeViewModel: SharedRecipeViewModel
) {
    var showPopup by remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }
    var selectedListId by remember { mutableStateOf<String?>(null) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    val appContext = LocalContext.current.applicationContext
    val userListsViewModel: UserListsViewModel =
        viewModel(factory = UserListsViewModelFactory(appContext))
    val addRecipeToListViewModel: AddRecipeToListViewModel =
        viewModel(factory = AddRecipeToListViewModelFactory(appContext))

    // Espera a que el userId esté disponible
    // Observar cambios en userId
    val userId = userListsViewModel.userId.collectAsState().value

    LaunchedEffect(userId) {
        if (userId.isNotEmpty()) {
            userListsViewModel.fetchUserLists()
        }
    }

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                // Botón superior

                if (recipe.autor._id == userId || userRole == "moderator") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TextButton(onClick = { navController.popBackStack() }) {
                            Text(
                                text = "< Back",
                                fontSize = 18.sp,
                                color = Color(0xFFFFA500),
                                textAlign = TextAlign.Start
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { showPopup = true },
                            modifier = Modifier
                                .shadow(5.dp, RoundedCornerShape(50))
                                .border(1.dp, Color(0xFFFFA500), RoundedCornerShape(50)),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text("+", fontSize = 20.sp, color = Color(0xFFFFA500))
                        }

                        IconButton(
                            onClick = {
                                showDeleteConfirmation = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Eliminar receta",
                                tint = Color.Red
                            )
                        }

                        if (showDeleteConfirmation) {

                            AlertDialog(
                                onDismissRequest = { showDeleteConfirmation = false },
                                title = {
                                    Text(text = stringResource(id = R.string.DeleteRecipe))
                                },
                                text = {
                                    Text(text = stringResource(id = R.string.SureDeleteRecipe))
                                },
                                confirmButton = {

                                    Button(
                                        onClick = {
                                            showDeleteConfirmation = false

                                            viewModel.deleteRecipe(recipe._id) {
                                                sharedRecipeViewModel.setDeletedRecipeId(recipe._id)
                                                navController.popBackStack()
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(Color.White),
                                        modifier = Modifier
                                            .border(
                                                1.5.dp,
                                                Color(0xFFFFA500),
                                                RoundedCornerShape(20.dp)
                                            )
                                    ) {
                                        Text(
                                            text = "si",
                                            color = Color(0xFFFFA500),
                                            fontSize = 16.sp
                                        )
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = { showDeleteConfirmation = false },
                                        colors = ButtonDefaults.buttonColors(Color(0xFFFFA500)),
                                    ) {
                                        Text(
                                            text = "No",
                                            color = Color.White,
                                        )
                                    }
                                }
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        TextButton(onClick = { navController.popBackStack() }) {
                            Text(
                                text = "< Back",
                                fontSize = 18.sp,
                                color = Color(0xFFFFA500),
                                textAlign = TextAlign.Start
                            )
                        }

                        Button(
                            onClick = { showPopup = true },
                            modifier = Modifier
                                .shadow(5.dp, RoundedCornerShape(50))
                                .border(1.dp, Color(0xFFFFA500), RoundedCornerShape(50)),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            shape = RoundedCornerShape(50)
                        ) {
                            Text("+", fontSize = 20.sp, color = Color(0xFFFFA500))
                        }
                    }
                }

                // Popup Principal
                if (showPopup) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Opciones de Lista",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Botón para Crear Nueva Lista
                            Button(
                                onClick = {
                                    showPopup = false
                                    navController.navigate(Routes.createListRoute(recipe._id))
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp)),
                                border = BorderStroke(1.dp, Color.White),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
                            ) {
                                Text(
                                    text = "Crear Nueva Lista",
                                    fontSize = 16.sp,
                                    color = Color(0xFFFFA500)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Botón para Mostrar Dropdown
                            Button(
                                onClick = {
                                    showDropdown = true
                                    showPopup = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp)),
                                border = BorderStroke(1.dp, Color.White),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
                            ) {
                                Text(
                                    text = "Agregar a Lista ya Existente",
                                    fontSize = 16.sp,
                                    color = Color(0xFFFFA500)
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            TextButton(onClick = { showPopup = false }) {
                                Text("Cancelar", fontSize = 14.sp, color = Color(0xFFFFA500))
                            }
                        }
                    }
                }

                // Dropdown para Seleccionar Lista
                if (showDropdown) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (userListsViewModel.isLoading) {
                                CircularProgressIndicator()
                            } else if (userListsViewModel.errorMessage != null) {
                                Text(
                                    text = "Error: ${userListsViewModel.errorMessage}",
                                    color = Color.Red
                                )
                            } else {
                                Text(
                                    text = "Selecciona una Lista",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                userListsViewModel.userLists.collectAsState().value.forEach { list ->
                                    TextButton(
                                        onClick = {
                                            selectedListId = list._id
                                            showDropdown = false
                                            addRecipeToListViewModel.addRecipeToList(
                                                listId = list._id,
                                                recipeId = recipe._id
                                            )
                                        }
                                    ) {
                                        Text(text = list.nameList, fontSize = 16.sp)
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            TextButton(onClick = { showDropdown = false }) {
                                Text("Cancelar", fontSize = 14.sp, color = Color(0xFFFFA500))
                            }
                        }
                    }
                }

                // Mostrar mensaje de éxito o error al agregar receta
                if (addRecipeToListViewModel.successMessage != null) {
                    Text(
                        text = addRecipeToListViewModel.successMessage!!,
                        color = Color.Green,
                        modifier = Modifier.padding(16.dp)
                    )
                } else if (addRecipeToListViewModel.errorMessage != null) {
                    Text(
                        text = addRecipeToListViewModel.errorMessage!!,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    item {
                        val recipeImageBitmap = base64ToBitmap(recipe.image)

                        if (recipeImageBitmap != null) {
                            Image(
                                bitmap = recipeImageBitmap.asImageBitmap(),
                                contentDescription = recipe.nameRecipe,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.FillWidth
                            )
                        } else {
                            // Imagen de marcador de posición en caso de error
                            Image(
                                painter = rememberAsyncImagePainter("https://via.placeholder.com/150"),
                                contentDescription = "Placeholder",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }

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
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
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
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
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
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
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
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
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