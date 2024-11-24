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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.presentation.user.viewmodels.HistoryViewModel
import com.example.cookbook.presentation.user.viewmodels.HistoryViewModelFactory

@Composable
fun UserView(navController: NavController) {
    var selectedTab by remember { mutableStateOf("My Recipes") }
    val appContext = LocalContext.current.applicationContext
    val historyViewModel: HistoryViewModel = viewModel(factory = HistoryViewModelFactory(appContext))

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.settingicon),
                            contentDescription = "Settings",
                            tint = Color(0xFFFFA500),
                            modifier = Modifier.size(50.dp)
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
                            .size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.userplaceholdericon),
                            contentDescription = "User Icon",
                            tint = Color(0xFFFFA500),
                            modifier = Modifier.size(400.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("@username", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                    Text("Followers: 108", fontSize = 22.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Lorem ipsum dolor sit amet consectetur adipiscing elit Ut et massa mi. Aliquam in hendrerit urna.",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(horizontal = 18.dp),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MenuButton(
                            icon = painterResource(id = R.drawable.bookicon),
                            text = "My Recipes",
                            //iconTint = Color(0xFF00FF00),
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
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                item {
                                    RecipeCard(
                                        title = "Carnitas Tacos",
                                        time = "25 min",
                                        image = painterResource(id = R.drawable.cookbooklogo3)
                                    )
                                }
                                item {
                                    RecipeCard(
                                        title = "California Roll",
                                        time = "20 min",
                                        image = painterResource(id = R.drawable.cookbooklogo3)
                                    )
                                }
                                item {
                                    RecipeCard(
                                        title = "Burger",
                                        time = "15 min",
                                        image = painterResource(id = R.drawable.cookbooklogo3)
                                    )
                                }
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
                                // Llama al ViewModel para cargar el historial
                                historyViewModel.viewHistory()

                                // Renderiza la lista de recetas
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
                modifier = Modifier.size(50.dp)
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

