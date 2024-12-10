package com.example.cookbook.presentation.finder.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.presentation.finder.viewmodels.SpecifiedFinderViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.finder.models.SearchRecipeBody
import com.example.cookbook.presentation.finder.network.IngredientByCategory
import com.example.cookbook.presentation.finder.network.SpecifiedFinderRepository
import com.example.cookbook.utils.base64ToBitmap

@Composable
// Comentario para cambios
fun SearchView(navController: NavController, viewModel: SpecifiedFinderViewModel) {
    var text by remember { viewModel.searchQuery }
    val results = viewModel.searchResponse.value

    // Variables de Keyboard
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val isLoading = viewModel.isLoading


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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, end = 10.dp)
            ) {
                TextButton(onClick = { navController.navigate(Routes.InitialFinderView) }) {
                    Text(
                        text = "< " + stringResource(id = R.string.Back),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFFA500),
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                }

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
                    .fillMaxHeight(.94f)
                    .align(Alignment.BottomCenter),
            ) {

                when {
                    isLoading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center),
                            color = Color(0xFFFFA500)
                        )
                    }

                    results.isEmpty() -> {
                        Text(
                            text = stringResource(id = R.string.NoResults),
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.Gray
                        )
                    }

                    results.isNotEmpty() -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            item {
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(75.dp)
                                        .background(Color.Transparent) // Sin color
                                )
                            }

                            items(results) { recipe ->
                                RecipeCards(
                                    recipe,
                                    navController
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 55.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.88f)
                        .shadow(8.dp, RoundedCornerShape(50.dp))
                        .background(Color.White, RoundedCornerShape(50.dp)),
                ) {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth(),
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
        }
    }
}

@Composable
fun RecipeCards(
    recipe: SearchRecipeBody, navController: NavController
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                navController.navigate("recipe_detail/${recipe._id}")
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(12.dp))
        ) {
            // Imagen de la receta
            val recipeImageBitmap = base64ToBitmap(recipe.image)

            if (recipeImageBitmap != null) {
                Image(
                    bitmap = recipeImageBitmap.asImageBitmap(),
                    contentDescription = recipe.nameRecipe,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(23.dp)),
                    contentScale = ContentScale.FillWidth
                )
            } else {
                // Imagen de marcador de posición en caso de error
                Image(
                    painter = rememberAsyncImagePainter("https://res.cloudinary.com/dlq6kbdw3/image/upload/v1733760478/cookbookplaceholder_p5owot.png"),
                    contentDescription = "Placeholder",
                    modifier = Modifier
                        .fillMaxSize()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            // Detalles de la receta
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x80000000))
                        .padding(start = 7.dp, end = 7.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = recipe.nameRecipe,
                        fontSize = 25.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                //Row de la parte inferior
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x80000000)) //se agrega un background con transparencia
                        .padding(start = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = recipe.category.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Left
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clock),
                                contentDescription = "Descripción del ícono",
                                tint = Color.Unspecified
                            )
                        }
                        Text(
                            text = recipe.preptime.toString() + " min",
                            fontSize = 18.sp,
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Left
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchView() {
    val appContext = LocalContext.current
    SearchView(
        rememberNavController(),
        SpecifiedFinderViewModel(
            FinderBodyRepository = SpecifiedFinderRepository,
            IngredientBody = IngredientByCategory,
            appContext
        )
    )
}