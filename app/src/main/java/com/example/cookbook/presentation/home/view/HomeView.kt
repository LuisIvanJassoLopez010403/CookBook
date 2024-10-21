package com.example.cookbook.presentation.home.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.login.views.LoginView

@Composable
fun HomeView(navController: NavController) {

    //variables para cambiar de color los botones al hacer click
    val Clicked = remember { mutableStateOf(false) }
    val iconColor = if (Clicked.value) Color(0xFFFF9800) else Color.White

    //Variables que proporcionan los elementos de las categorias

    //Categoria Popular
    val PopularImages = listOf(
        R.drawable.picza,
        R.drawable.tacosp,
        R.drawable.hamburguer,
        R.drawable.hotdog,
        R.drawable.sushi,
    )

    val PopularTitles = listOf(
        stringResource(id = R.string.Pizza),
        stringResource(id = R.string.Tacos),
        stringResource(id = R.string.Hamburger),
        stringResource(id = R.string.HotDog),
        stringResource(id = R.string.Sushi)
    )

    val PopularIngredients = listOf(
        listOf(
            stringResource(id = R.string.Sourdough), stringResource(id = R.string.Flour), stringResource(id = R.string.Salt),
            stringResource(id = R.string.Water), stringResource(id = R.string.olivoil), stringResource(id = R.string.Tomato),
            stringResource(id = R.string.Cheese), stringResource(id = R.string.Pepperoni)
        ),
        listOf(
            stringResource(id = R.string.Tortilla), stringResource(id = R.string.Meat), stringResource(id = R.string.Onion),
            stringResource(id = R.string.Cilantro), stringResource(id = R.string.Lime), stringResource(id = R.string.Sauce),
            stringResource(id = R.string.Avocado)
        ),
        listOf(
            stringResource(id = R.string.Bread), stringResource(id = R.string.Meat), stringResource(id = R.string.Lettuce),
            stringResource(id = R.string.Tomato), stringResource(id = R.string.Cheese), stringResource(id = R.string.Ketchup),
            stringResource(id = R.string.Mustard), stringResource(id = R.string.Pickles)
        ),
        listOf(
            stringResource(id = R.string.Bread), stringResource(id = R.string.Sausage), stringResource(id = R.string.Ketchup),
            stringResource(id = R.string.Mustard), stringResource(id = R.string.Onion), stringResource(id = R.string.ChiliPeper),
            stringResource(id = R.string.Avocado)
        ),
        listOf(
            stringResource(id = R.string.Rice), stringResource(id = R.string.Seaweed), stringResource(id = R.string.Salmon),
            stringResource(id = R.string.Cucumber), stringResource(id = R.string.Avocado), stringResource(id = R.string.Soysauce),
            stringResource(id = R.string.Wasabi), stringResource(id = R.string.Ginger)
        )
    )

    val PopularDescriptions = listOf(
        stringResource(id = R.string.PiczaDescription),
        stringResource(id = R.string.TacosDescription),
        stringResource(id = R.string.HamburgerDescription),
        stringResource(id = R.string.HotDogDescription),
        stringResource(id = R.string.SushiDescription)
    )

    //Categoria Mexicana
    val MexicanImages = listOf(
        R.drawable.chilaquiles,
        R.drawable.pozole,
        R.drawable.sopes,
        R.drawable.molletes,
        R.drawable.burritos,
    )

    val MexicanTitles = listOf(
        stringResource(id = R.string.Chilaquiles),
        stringResource(id = R.string.Pozole),
        stringResource(id = R.string.Sopes),
        stringResource(id = R.string.Molletes),
        stringResource(id = R.string.Burritos)
    )

    val MexicanIngredients = listOf(
        listOf(
            stringResource(id = R.string.Tortilla), stringResource(id = R.string.Sauce), stringResource(id = R.string.Cream),
            stringResource(id = R.string.Cheese), stringResource(id = R.string.Chicken), stringResource(id = R.string.Onion),
            stringResource(id = R.string.Avocado)
        ),
        listOf(
            stringResource(id = R.string.Corn), stringResource(id = R.string.Meat), stringResource(id = R.string.Onion),
            stringResource(id = R.string.Cilantro), stringResource(id = R.string.Lime), stringResource(id = R.string.Radishes),
            stringResource(id = R.string.Lettuce)
        ),
        listOf(
            stringResource(id = R.string.Flour), stringResource(id = R.string.Beans), stringResource(id = R.string.Cream),
            stringResource(id = R.string.Cheese), stringResource(id = R.string.Meat), stringResource(id = R.string.Lettuce),
            stringResource(id = R.string.Sauce)
        ),
        listOf(
            stringResource(id = R.string.Bread), stringResource(id = R.string.Beans), stringResource(id = R.string.Cheese),
            stringResource(id = R.string.Cream), stringResource(id = R.string.Sauce), stringResource(id = R.string.Chorizo),
            stringResource(id = R.string.Avocado)
        ),
        listOf(
            stringResource(id = R.string.Tortilla), stringResource(id = R.string.Beans), stringResource(id = R.string.Cheese),
            stringResource(id = R.string.Meat), stringResource(id = R.string.Sauce)
        )
    )

    val MexicanDescriptions = listOf(
        stringResource(id = R.string.ChilaquilesDescription),
        stringResource(id = R.string.PozoleDescription),
        stringResource(id = R.string.SopesDescription),
        stringResource(id = R.string.Molletes),
        stringResource(id = R.string.BurritosDescription)
    )

    //Categoria de Postres
    val DessertsImages = listOf(
        R.drawable.flan,
        R.drawable.tiramisu,
        R.drawable.brownie,
        R.drawable.cheesecake,
        R.drawable.pandelote
    )

    val DessertsTitles = listOf(
        stringResource(id = R.string.Flan),
        stringResource(id = R.string.Tiramisu),
        stringResource(id = R.string.Brownie),
        stringResource(id = R.string.Cheesecake),
        stringResource(id = R.string.Cornbread)
    )

    val DessertsIngredients = listOf(
        listOf(
            stringResource(id = R.string.Milk), stringResource(id = R.string.Eggs), stringResource(id = R.string.Sugar),
            stringResource(id = R.string.Vanilla), stringResource(id = R.string.Caramel), stringResource(id = R.string.Gelatin)
        ),
        listOf(
            stringResource(id = R.string.Coffee), stringResource(id = R.string.Eggs), stringResource(id = R.string.Sugar),
            stringResource(id = R.string.Cheese), stringResource(id = R.string.Cocoa), stringResource(id = R.string.Gelatin)
        ),
        listOf(
            stringResource(id = R.string.Chocolate), stringResource(id = R.string.Eggs), stringResource(id = R.string.Sugar),
            stringResource(id = R.string.Flour), stringResource(id = R.string.Butter), stringResource(id = R.string.Vanilla)
        ),
        listOf(
            stringResource(id = R.string.Cheese), stringResource(id = R.string.Eggs), stringResource(id = R.string.Sugar),
            stringResource(id = R.string.Flour), stringResource(id = R.string.Butter), stringResource(id = R.string.Vanilla)
        ),
        listOf(
            stringResource(id = R.string.Flour), stringResource(id = R.string.Eggs), stringResource(id = R.string.Sugar),
            stringResource(id = R.string.Milk), stringResource(id = R.string.Butter), stringResource(id = R.string.Vanilla)
        )
    )

    val DessertsDescriptions = listOf(
        stringResource(id = R.string.FlanDescription),
        stringResource(id = R.string.TiramisuDescription),
        stringResource(id = R.string.BrownieDescription),
        stringResource(id = R.string.CheesecakeDescription),
        stringResource(id = R.string.CornBreadDescription)
    )

    //Se utiliza un Scaffold para tener la barra de navegación en la parte inferior
    Scaffold(
        bottomBar = {
            BottomNavBarView(navController = navController)
        }
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
            ){
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
                item {
                    //Se llama a la funcion de categorias para insertar el titulo de la categoria
                    LazyColumnHome(
                        Category = stringResource(id = R.string.Popular)
                    )
                }
                item {
                    //Se utiliza un Lazy Row para poder hacer scroll horizontal entre las recetas
                    LazyRow(
                        modifier = Modifier.padding(start = 0.dp)
                    ) {
                        items(5) { index ->
                            //Se utiliza un Box clickable para almacenar los elementos de las recetas y acceder a ellas
                            Box(
                                modifier = Modifier
                                    .width(211.dp)
                                    .height(357.dp)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(23.dp))
                                    //Se utiliza este painter para poner la imagen como fondo del box
                                    .paint(painterResource(id = PopularImages[index])) //Se importa la variable de popularImages
                                    .clickable(onClick = {
                                        navController.navigate(Routes.MyRecipeView)  //Ruta a la pantalla de receta en cuestion
                                    })
                            ) {
                                Image(
                                    painter = painterResource(id = PopularImages[index]), //se llama la variable PopularImages para implementar una imagen distinta por receta
                                    contentDescription = "Descripción de la imagen",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(23.dp)),
                                    contentScale = ContentScale.FillBounds
                                )

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
                                            .padding(start = 5.dp, end = 5.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        PopularTitles[index].let {
                                            Text(
                                                text = it, //Se llama al listado de variables para los titulos
                                                fontSize = 25.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFFFFFFF),
                                                textAlign = TextAlign.Left
                                            )
                                        }

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

                                    // Row Parte inferior
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(130.dp)
                                            .background(Color(0x80000000)),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(130.dp)
                                                .padding(start = 4.dp, end = 4.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            //Columna de ingredientes
                                            Column(
                                            ) {
                                                Text(
                                                    text = stringResource(id = R.string.Ingredients),
                                                    fontStyle = FontStyle.Italic,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center
                                                )
                                                // Recorre la lista de ingredientes y muestra cada uno
                                                PopularIngredients[index].forEach { ingredient ->
                                                    Text(
                                                        text = "• $ingredient",
                                                        color = Color.White,
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Normal,
                                                        lineHeight = 20.sp,
                                                        modifier = Modifier.padding(vertical = 2.dp)
                                                    )
                                                }
                                            }

                                            //Columna de Descripciones
                                            Column(
                                            ) {
                                                Text(
                                                    text = stringResource(id = R.string.Description),
                                                    fontStyle = FontStyle.Italic,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center
                                                )
                                                PopularDescriptions[index].forEach { Description ->
                                                    Text(
                                                        text = PopularDescriptions[index],
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
                                }
                            }
                        }
                    }
                }
                item {
                    LazyColumnHome(
                        Category = stringResource(id = R.string.Mexican)
                    )
                }
                item {
                    LazyRow(
                        modifier = Modifier.padding(start = 0.dp)
                    ) {
                        items(5) { index ->
                            Box(
                                modifier = Modifier
                                    .width(211.dp)
                                    .height(357.dp)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(23.dp))
                                    .paint(painterResource(id = MexicanImages[index]))
                                    .clickable(onClick = {
                                        navController.navigate(Routes.MyRecipeView)
                                    })
                            ) {
                                Image(
                                    painter = painterResource(id = MexicanImages[index]),
                                    contentDescription = "Descripción de la imagen",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(23.dp)),
                                    contentScale = ContentScale.FillBounds
                                )

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0x80000000))
                                            .padding(start = 5.dp, end = 5.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        MexicanTitles[index].let {
                                            Text(
                                                text = it,
                                                fontSize = 25.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFFFFFFF),
                                                textAlign = TextAlign.Left
                                            )
                                        }

                                        IconButton(
                                            onClick = { Clicked.value = !Clicked.value },
                                            modifier = Modifier
                                                .size(32.dp)
                                                .align(Alignment.CenterVertically)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.heart),
                                                contentDescription = "Descripción del ícono",
                                                tint = iconColor
                                            )
                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(130.dp)
                                            .background(Color(0x80000000)),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(130.dp)
                                                .padding(start = 4.dp, end = 4.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Column(
                                            ) {
                                                Text(
                                                    text = stringResource(id = R.string.Ingredients),
                                                    fontStyle = FontStyle.Italic,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center
                                                )

                                                MexicanIngredients[index].forEach { ingredient ->
                                                    Text(
                                                        text = "• $ingredient",
                                                        color = Color.White,
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Normal,
                                                        lineHeight = 20.sp,
                                                        modifier = Modifier.padding(vertical = 2.dp)
                                                    )
                                                }
                                            }

                                            Column(
                                            ) {
                                                Text(
                                                    text = stringResource(id = R.string.Description),
                                                    fontStyle = FontStyle.Italic,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center
                                                )
                                                MexicanDescriptions[index].forEach { Description ->
                                                    Text(
                                                        text = MexicanDescriptions[index],
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
                                }
                            }
                        }
                    }
                }
                item {
                    LazyColumnHome(
                        Category = stringResource(id = R.string.Desserts)
                    )
                }
                item {
                    LazyRow(
                        modifier = Modifier.padding(start = 0.dp)
                    ) {
                        items(5) { index ->
                            Box(
                                modifier = Modifier
                                    .width(211.dp)
                                    .height(357.dp)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(23.dp))
                                    .paint(painterResource(id = DessertsImages[index]))
                                    .clickable(onClick = {
                                        navController.navigate(Routes.MyRecipeView)
                                    })
                            ) {
                                Image(
                                    painter = painterResource(id = DessertsImages[index]),
                                    contentDescription = "Descripción de la imagen",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(23.dp)),
                                    contentScale = ContentScale.FillBounds
                                )

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0x80000000))
                                            .padding(start = 5.dp, end = 5.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        DessertsTitles[index].let {
                                            Text(
                                                text = it,
                                                fontSize = 25.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFFFFFFF),
                                                textAlign = TextAlign.Left
                                            )
                                        }

                                        IconButton(
                                            onClick = { Clicked.value = !Clicked.value },
                                            modifier = Modifier
                                                .size(32.dp) // Ajusta el tamaño aquí
                                                .align(Alignment.CenterVertically)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.heart),
                                                contentDescription = "Descripción del ícono",
                                                tint = iconColor // Ajusta el color del ícono
                                            )
                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(130.dp)
                                            .background(Color(0x80000000)),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.Bottom
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(130.dp)
                                                .padding(start = 4.dp, end = 4.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.Top
                                        ) {
                                            Column(
                                            ) {
                                                Text(
                                                    text = stringResource(id = R.string.Ingredients),
                                                    fontStyle = FontStyle.Italic,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White,
                                                    textAlign = TextAlign.Center
                                                )
                                                DessertsIngredients[index].forEach { ingredient ->
                                                    Text(
                                                        text = "• $ingredient",
                                                        color = Color.White,
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Normal,
                                                        lineHeight = 20.sp,
                                                        modifier = Modifier.padding(vertical = 2.dp)
                                                    )
                                                }
                                            }

                                            Column(
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
                                                        text = DessertsDescriptions[index],
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
                            }
                        }
                    }
                }
            }
        }
    }
}

//Se crea una Funcion para establecer el diseño del titulo de las categorias
@Composable
fun LazyColumnHome ( Category: String){
    Row (
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp)
    ){
        Text(
            Category,
            fontStyle = FontStyle.Italic,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeView() {
    HomeView(rememberNavController())
}