package com.example.cookbook.presentation.home.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.presentation.user.RecipeCard
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeView(navController: NavController) {

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

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                item {
                    LazyColumnHome(
                        Category = "Popular"
                    )
                }
                item {
                    LazyRow(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(4) { index ->
                            // Elementos dentro del LazyRow (scroll horizontal)
                            Box(
                                modifier = Modifier
                                    .width(211.dp)
                                    .height(357.dp)
                                    .padding(15.dp)
                                    .clip(RoundedCornerShape(23.dp))
                                    .paint(
                                        painterResource(id = R.drawable.picza)
                                    )
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.picza),
                                    contentDescription = "Descripción de la imagen",
                                    modifier = Modifier
                                        .fillMaxSize()  // Asegúrate de que la imagen llene el Box
                                        .clip(RoundedCornerShape(23.dp)),
                                    contentScale = ContentScale.FillBounds
                                )

                                Row (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(15.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = "Pizza",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFFFFFF),
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        text = "Ejele",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFFFFFF),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    LazyColumnHome(
                        Category = "Mexican"
                    )
                }
                item {
                    LazyRow(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(4) { index ->
                            // Elementos dentro del LazyRow (scroll horizontal)
                            Box(
                                modifier = Modifier
                                    .width(211.dp)
                                    .height(357.dp)
                                    .padding(15.dp)
                                    .clip(RoundedCornerShape(23.dp))
                                    .paint(
                                        painterResource(id = R.drawable.chilaquiles)
                                    )
                            ){
                                Image(
                                    painter = painterResource(id = R.drawable.chilaquiles),
                                    contentDescription = "Descripción de la imagen",
                                    modifier = Modifier
                                        .fillMaxSize()  // Asegúrate de que la imagen llene el Box
                                        .clip(RoundedCornerShape(23.dp)),
                                    contentScale = ContentScale.FillBounds
                                )

                                Row (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(15.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text(
                                        text = "Pizza",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFFFFFF),
                                        textAlign = TextAlign.Center
                                    )

                                    Text(
                                        text = "Ejele",
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFFFFFF),
                                        textAlign = TextAlign.Center
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

@Composable
fun HorizontalScrollExample() {
    // Recuerda agregar un state para controlar el scroll
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)  // Habilita el scroll horizontal
            .fillMaxWidth()
    ) {
        // Elementos dentro del Row que serán desplazables horizontalmente
        Text(text = "Elemento 1", modifier = Modifier.padding(16.dp))
        Text(text = "Elemento 2", modifier = Modifier.padding(16.dp))
        Text(text = "Elemento 3", modifier = Modifier.padding(16.dp))
        Text(text = "Elemento 4", modifier = Modifier.padding(16.dp))
        Text(text = "Elemento 5", modifier = Modifier.padding(16.dp))
    }
}