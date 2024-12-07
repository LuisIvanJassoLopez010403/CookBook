package com.example.cookbook.presentation.user.views

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.cookbook.navigation.Routes
import com.example.cookbook.preferences.clearToken
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModel
import com.example.cookbook.presentation.lists.viewmodels.UserListsViewModelFactory
import com.example.cookbook.presentation.lists.views.UserListsView
import com.example.cookbook.presentation.user.viewmodels.HistoryViewModel
import com.example.cookbook.presentation.user.viewmodels.HistoryViewModelFactory
import com.example.cookbook.presentation.user.viewmodels.UserDetailsViewModel
import com.example.cookbook.presentation.user.viewmodels.UserDetailsViewModelFactory
import com.example.cookbook.presentation.user.viewmodels.UserRecipesViewModel
import com.example.cookbook.presentation.user.viewmodels.UserRecipesViewModelFactory
import com.example.cookbook.utils.base64ToBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserView(navController: NavController) {
    var selectedTab by remember { mutableStateOf("My Recipes") }
    val appContext1 = LocalContext.current.applicationContext
    val appContext2 = LocalContext.current.applicationContext
    val appContext3 = LocalContext.current.applicationContext
    val historyViewModel: HistoryViewModel = viewModel(factory = HistoryViewModelFactory(appContext1))
    val userRecipesViewModel: UserRecipesViewModel = viewModel(factory = UserRecipesViewModelFactory(appContext2))
    val userDetailsViewModel: UserDetailsViewModel = viewModel(factory = UserDetailsViewModelFactory(appContext3))

    var showLogoutConfirmation by remember { mutableStateOf(false) }

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp, top = 10.dp)
                ) {
                    Text(
                        text = "CookBook",
                        fontStyle = FontStyle.Italic,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF9800),
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                    //horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = { showLogoutConfirmation = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.logouticon),
                            contentDescription = "Cerrar Sesión",
                            tint = Color(0xFFFFA500),
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    if (showLogoutConfirmation) {
                        val context = LocalContext.current

                        AlertDialog(
                            onDismissRequest = { showLogoutConfirmation = false },
                            title = {
                                Text(text = "Cerrar Sesión")
                            },
                            text = {
                                Text("¿Estás seguro de que deseas cerrar sesión?")
                            },
                            confirmButton = {
                                Button(onClick = {
                                    showLogoutConfirmation = false

                                    // Limpiar el token y redirigir
                                    CoroutineScope(Dispatchers.IO).launch {
                                        clearToken(context) // Eliminar el token del DataStore
                                    }

                                    navController.navigate(Routes.TitleView) {
                                        popUpTo(Routes.UserView) { inclusive = true } // Asegura que el stack de navegación se limpie
                                    }
                                }) {
                                    Text("Sí")
                                }
                            },
                            dismissButton = {
                                Button(onClick = { showLogoutConfirmation = false }) {
                                    Text("No")
                                }
                            }
                        )
                    }


                    Spacer(modifier = Modifier.width(300.dp))
                    //Spacer(modifier = Modifier.width(100.dp))

                    IconButton(onClick = {
                        navController.navigate(Routes.UserEditView) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.settingicon),
                            contentDescription = "Settings",
                            tint = Color(0xFFFFA500),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFFFFA500), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        val profileImageBitmap = userDetailsViewModel.profilePicture.let {
                            if (it.isEmpty()) null else base64ToBitmap(it)
                        }

                        if (profileImageBitmap != null) {
                            Image(
                                bitmap = profileImageBitmap.asImageBitmap(),
                                contentDescription = "Profile Picture",
                                modifier = Modifier.size(150.dp) // Tamaño de la imagen
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.userplaceholdericon),
                                contentDescription = "Profile Placeholder",
                                modifier = Modifier.size(150.dp) // Tamaño de la imagen por defecto
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = userDetailsViewModel.username,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = userDetailsViewModel.bio,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 18.dp),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MenuButton(
                            icon = painterResource(id = R.drawable.bookicon),
                            text = "My Recipes",
                            isSelected = selectedTab == "My Recipes",
                            onClick = { selectedTab = "My Recipes" }
                        )
                        MenuButton(
                            icon = painterResource(id = R.drawable.clipboardicon),
                            text = "Lists",
                            isSelected = selectedTab == "Lists",
                            onClick = { selectedTab = "Lists" }
                        )
                        MenuButton(
                            icon = painterResource(id = R.drawable.usercheckicon),
                            text = "Following",
                            isSelected = selectedTab == "Following",
                            onClick = { selectedTab = "Following" }
                        )
                        MenuButton(
                            icon = painterResource(id = R.drawable.historyicon),
                            text = "History",
                            isSelected = selectedTab == "History",
                            onClick = { selectedTab = "History" }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mostrar contenido según el tab seleccionado
                    when (selectedTab) {
                        "My Recipes" -> {
                            Column(modifier = Modifier.fillMaxSize()) {
                                userRecipesViewModel.getUserRecipes()
                                UserRecipesList(viewModel = userRecipesViewModel, navController = navController)
                            }
                        }

                        "Lists" -> {
                            Column(modifier = Modifier.fillMaxSize()) {
                                val appContext = LocalContext.current.applicationContext
                                val userListsViewModel: UserListsViewModel = viewModel(factory = UserListsViewModelFactory(appContext))

                                // Llama a la función de UserListsView
                                UserListsView(viewModel = userListsViewModel, navController = navController)
                            }
                        }
                        "History" -> {
                            Column(modifier = Modifier.fillMaxSize()) {
                                historyViewModel.viewHistory()
                                RecipeHistoryList(viewModel = historyViewModel, navController = navController)
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavBarView(navController = navController)
        }
    )
    userDetailsViewModel.fetchUserDetails()
}

@Composable
fun MenuButton(
    icon: Painter,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val selectedColor = Color(0xFFFFA500)
    val defaultColor = Color.Gray

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = if (isSelected) selectedColor else defaultColor,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = text,
            fontSize = 16.sp,
            color = if (isSelected) selectedColor else defaultColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserView() {
    UserView(rememberNavController())
}

