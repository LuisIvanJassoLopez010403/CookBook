package com.example.cookbook.presentation.user.views

import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.user.viewmodels.HistoryViewModel
import com.example.cookbook.presentation.user.viewmodels.HistoryViewModelFactory
import com.example.cookbook.presentation.user.viewmodels.UserDetailsViewModel
import com.example.cookbook.presentation.user.viewmodels.UserDetailsViewModelFactory
import com.example.cookbook.presentation.user.viewmodels.UserRecipesViewModel
import com.example.cookbook.presentation.user.viewmodels.UserRecipesViewModelFactory

@Composable
fun UserView(navController: NavController) {
    var selectedTab by remember { mutableStateOf("My Recipes") }
    val appContext1 = LocalContext.current.applicationContext
    val appContext2 = LocalContext.current.applicationContext
    val appContext3 = LocalContext.current.applicationContext
    val historyViewModel: HistoryViewModel = viewModel(factory = HistoryViewModelFactory(appContext1))
    val userRecipesViewModel: UserRecipesViewModel = viewModel(factory = UserRecipesViewModelFactory(appContext2))
    val userDetailsViewModel: UserDetailsViewModel = viewModel(factory = UserDetailsViewModelFactory(appContext3))

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
                ) {
                    IconButton(onClick = { navController.navigate(Routes.TitleView) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.logouticon),
                            contentDescription = "Settings",
                            tint = Color(0xFFFFA500),
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(300.dp))

                    IconButton(onClick = { navController.navigate(Routes.UserEditView) }) {
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
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        val profileImagePainter = rememberAsyncImagePainter(
                            model = if (userDetailsViewModel.profilePicture == "default_placeholder_url") {
                                null
                            } else {
                                userDetailsViewModel.profilePicture
                            },
                            placeholder = painterResource(id = R.drawable.userplaceholdericon),
                            error = painterResource(id = R.drawable.userplaceholdericon)
                        )

                        Image(
                            painter = profileImagePainter,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(100.dp)
                        )
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
                            onClick = { selectedTab = "My Recipes" }
                        )
                        MenuButton(
                            icon = painterResource(id = R.drawable.clipboardicon),
                            text = "Lists",
                            onClick = { selectedTab = "Lists" }
                        )
                        MenuButton(
                            icon = painterResource(id = R.drawable.usercheckicon),
                            text = "Following",
                            onClick = { selectedTab = "Following" }
                        )
                        MenuButton(
                            icon = painterResource(id = R.drawable.historyicon),
                            text = "History",
                            onClick = { selectedTab = "History" }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mostrar contenido según el tab seleccionado
                    when (selectedTab) {
                        "My Recipes" -> {
                            Column(modifier = Modifier.fillMaxSize()) {
                                userRecipesViewModel.getUserRecipes()
                                UserRecipesList(userRecipesViewModel)
                            }
                        }

                        "Lists" -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                item {
                                    RecipeCard(
                                        title = "Favorites",
                                        time = "Recipes added: 6",
                                        image = painterResource(id = R.drawable.cookbooklogo3)
                                    )
                                }
                                item {
                                    RecipeCard(
                                        title = "Seafood",
                                        time = "Recipes added: 7",
                                        image = painterResource(id = R.drawable.cookbooklogo3)
                                    )
                                }
                                item {
                                    RecipeCard(
                                        title = "Japanese Cuisine",
                                        time = "Recipes added: 14",
                                        image = painterResource(id = R.drawable.cookbooklogo3)
                                    )
                                }
                            }
                        }
                        "History" -> {
                            Column(modifier = Modifier.fillMaxSize()) {
                                historyViewModel.viewHistory()
                                RecipeHistoryList(historyViewModel)
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
    iconTint: Color = Color(0xFFFFA500),
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = iconTint,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun RecipeCard(title: String, time: String, image: Painter) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(painter = image, contentDescription = title, modifier = Modifier.size(150.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(title, fontSize = 16.sp)
        Text(time, fontSize = 12.sp, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserView() {
    UserView(rememberNavController())
}

