package com.example.cookbook.presentation.home.home.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.Category
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.finder.views.LazyRowCategories
import com.example.cookbook.presentation.finder.views.SearchView
import com.example.cookbook.presentation.home.home.models.HomeResponse
import com.example.cookbook.presentation.home.home.network.HomeRepository
import com.example.cookbook.presentation.home.home.viewmodels.HomeViewModel
import com.example.cookbook.utils.base64ToBitmap

@Composable
fun HomeView(navController: NavController, viewModel: HomeViewModel) {

    //variables para cambiar de color los botones al hacer click
    val Clicked = remember { mutableStateOf(false) }
    val iconColor = if (Clicked.value) Color(0xFFFF9800) else Color.White
    val groupedRecipes = viewModel.groupRecipesByCategory(viewModel.recipesbycategory)

    //Se utiliza un Scaffold para tener la barra de navegación en la parte inferior
    Scaffold(
        bottomBar = {
            BottomNavBarView(navController = navController)
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            //En este Row se muestra el titulo de la aplicacion
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

            //Se utiliza un Lazy Column para poder hacer scroll entre las categorias de recetas
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                items(groupedRecipes) { response ->
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp)
                    ) {
                        Text(
                            text = response.category,
                            fontStyle = FontStyle.Italic,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                    }
                    LazyRowRecipes(response, navController)
                }
            }
        }
    }
}


@Composable
fun LazyRowRecipes(response: HomeResponse, navController: NavController) {
    val Clicked = remember { mutableStateOf(false) }
    val iconColor = if (Clicked.value) Color(0xFFFF9800) else Color.LightGray
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.padding(start = 0.dp)
        ) {
            items(response.recipes) { recipes ->
                Box(
                    modifier = Modifier
                        .width(211.dp)
                        .height(357.dp)
                        .padding(10.dp)
                        .clip(RoundedCornerShape(23.dp))
                        .clickable(onClick = {
                            navController.navigate("recipe_detail/${recipes._id}")
                        })
                ) {
                    val recipeImageBitmap = base64ToBitmap(recipes.image)

                    if (recipeImageBitmap != null) {
                        Image(
                            bitmap = recipeImageBitmap.asImageBitmap(),
                            contentDescription = recipes.nameRecipe,
                            modifier = Modifier
                           .fillMaxSize()
                           .clip(RoundedCornerShape(23.dp)),
                        contentScale = ContentScale.FillHeight
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
//                    Image(
//                        painter = rememberAsyncImagePainter(recipes.image),
//                        contentDescription = "Descripción de la imagen",
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .clip(RoundedCornerShape(23.dp)),
//                        contentScale = ContentScale.FillHeight,
//                        //contentScale = ContentScale.FillBounds
//                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Row de la parte superior
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0x80000000)) //se agrega un background con transparencia
                                .padding(start = 7.dp, end = 5.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = recipes.nameRecipe,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Left
                            )
                        }

                        // Row Parte inferior
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(Color(0x80000000)),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(start = 4.dp, end = 0.dp),
                                //horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {
                                //Columna de Descripciones
                                Column(
                                    modifier = Modifier
                                    //.fillMaxWidth(0.5f)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.Description),
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        text = recipes.description,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        lineHeight = 20.sp,
                                        textAlign = TextAlign.Left,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }

                    /*Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 7.dp, end = 5.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Top
                        ){
                            IconButton(
                                onClick = { Clicked.value = !Clicked.value },
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.CenterVertically)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.heart),
                                    contentDescription = "Descripción del ícono",
                                    tint = iconColor // Ajusta el color del ícono
                                )
                            }
                        }
                    }*/
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    HomeView(
        rememberNavController(),
        HomeViewModel(HomeBodyrepository = HomeRepository)
    )
}