package com.example.cookbook.presentation.finder.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.Category
import com.example.cookbook.Ingredient
import com.example.cookbook.IngredientDetails
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.presentation.finder.models.IngredientResponse
import com.example.cookbook.presentation.finder.models.SearchIngredient
import com.example.cookbook.presentation.finder.network.IngredientByCategory
import com.example.cookbook.presentation.finder.network.SpecifiedFinderRepository
import com.example.cookbook.presentation.finder.viewmodels.SpecifiedFinderViewModel
import com.example.cookbook.presentation.home.home.models.HomeResponse

@Composable
fun InitialFinderView(navController: NavController, viewModel: SpecifiedFinderViewModel) {
    var text by remember { viewModel.searchQuery }
    val groupedIngredient = viewModel.groupingredientsbycategory(viewModel.ingredientsbycategory)

    // Variables de Keyboard
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        bottomBar = {
            BottomNavBarView(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .fillMaxHeight()
                .background(Color(0xFFF6F6F6))
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                }
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, end = 10.dp)
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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 55.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .width(365.dp)
                        .shadow(8.dp, RoundedCornerShape(50.dp))
                        .background(Color.White, RoundedCornerShape(50.dp)),
                ) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                            }
                        ),

                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.Search),
                            )
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight(0.83f)
                    .fillMaxWidth(0.91f)
                    .align(Alignment.BottomCenter),
                //contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .border(
                            1.5.dp, Color(0xFFFFA500),
                            RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                        )
                        .background(Color.White, RoundedCornerShape(25.dp)),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        item {
                            LazyColumnHome("Categories")
                        }
                        item {
                            LazyRow(
                                modifier = Modifier
                                    .padding(start = 0.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(viewModel.getCategoryList()) { Category ->
                                    LazyRowCategories(Category, viewModel)
                                }
                            }
                        }
                        item {
                            LazyColumnHome("Ingredients")
                        }
                        items(groupedIngredient) { ingredientResponse ->
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 15.dp)
                            ) {
                                Text(
                                    text = ingredientResponse.category,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center
                                )
                            }

                            LazyRow(
                                modifier = Modifier
                                    .padding(start = 0.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(ingredientResponse.ingredients) { ingredient ->
                                    LazyRowIngredients(ingredient, viewModel)
                                }
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.15f)
                    .fillMaxWidth(0.91f)
                    .background(Color.Black.copy(alpha = 0.20f))
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.searchRecipes(text)
                            navController.navigate("SearchView")
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .fillMaxWidth(0.45f)
                            .fillMaxHeight(0.45f)
                            .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp))
                            .shadow(10.dp, RoundedCornerShape(25.dp))
                    ) {
                        Text(
                            text = "Search",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFA500),
                            fontSize = 23.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LazyColumnHome(Category: String) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp)
    ) {
        Text(
            Category,
            fontStyle = FontStyle.Italic,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LazyRowCategories(category: Category, viewModel: SpecifiedFinderViewModel) {
    var isClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(105.dp)
            .clickable(onClick = {
                isClicked = !isClicked
                viewModel.toggleCategoriesSelection(category._id)
            })
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .padding(10.dp)
                .border(3.dp, if (isClicked) Color(0xFF00FF18) else Color.Black, CircleShape)
                .background(Color.Transparent, CircleShape)
                .align(Alignment.TopCenter)
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Category",
                tint = Color(0xFF000000),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
                .padding(bottom = 5.dp)
                .align(Alignment.BottomCenter)
                .zIndex(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = category.name,
                    fontStyle = FontStyle.Italic,
                    fontSize = 17.sp,
                    color = if (isClicked) Color(0xFFFF9800) else Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun LazyRowIngredients(ingredient: SearchIngredient, viewModel: SpecifiedFinderViewModel) {
    var isClicked by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(105.dp)
            .clickable(onClick = {
                isClicked = !isClicked
                viewModel.toggleIngredientSelection(ingredient._id)
            })
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .padding(10.dp)
                .border(3.dp, if (isClicked) Color(0xFF00FF18) else Color.Black, CircleShape)
                .background(Color.Transparent, CircleShape)
                .align(Alignment.TopCenter)
        ) {
            Icon(
                painter = rememberAsyncImagePainter(ingredient.icon),
                contentDescription = "Category",
                tint = Color(0xFF000000),
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
                .padding(bottom = 5.dp)
                .align(Alignment.BottomCenter)
                .zIndex(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = ingredient.nameIngredient,
                    fontStyle = FontStyle.Italic,
                    fontSize = 17.sp,
                    color = if (isClicked) Color(0xFFFF9800) else Color(0xFF000000),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBuscadorinicialView() {
    InitialFinderView(
        rememberNavController(), SpecifiedFinderViewModel(
            FinderBodyRepository = SpecifiedFinderRepository,
            IngredientBody = IngredientByCategory
        )
    )
}
