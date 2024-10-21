package com.example.cookbook.presentation.myRecipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.navigation.Routes

@Composable
fun MyRecipeView(navController: NavController) {
    Scaffold(
        topBar = {
            // Barra superior fija con "Back" y botón "Edit"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { navController.navigate(Routes.HomeView) }) {
                    Text(
                        text = "< Back",
                        fontSize = 18.sp,
                        color = Color(0xFFFFA500),
                        modifier = Modifier,
                        textAlign = TextAlign.Center
                    )
                }

                // Botón "Edit"
                Button(
                    onClick = { /* Acción de edición */ }, // Navega hasta la pantalla de edición del usuario.
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF9800)),
                    modifier = Modifier
                        .width(80.dp)
                        .height(37.dp)
                        .clip(RoundedCornerShape(50)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Edit",
                        color = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Título "Tacos de Carnitas" en el centro
                item {
                    Text(
                        text = "Tacos de Carnitas",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }

                // Imagen de los tacos de carnitas
                item {
                    Image(
                        painter = painterResource(id = R.drawable.carnitasimage),
                        contentDescription = "Tacos de Carnitas",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .padding(16.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                // Receta para que se vea estetica, modificar en un futuro para
                item {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Prep Time: ")
                            }
                            append("🕒 25 min\n\n")

                            withStyle(style = SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                                append("Ingredientes:\n\n")
                            }

                            // Apartado de cantidades e ingredientes.
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("2 1/2 ")
                            }
                            append("lb de costillas de cerdo estilo campestre deshuesadas, cortadas en trozos de 1 pulgada.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("2 1/2 ")
                            }
                            append("cucharaditas de sal.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("cucharadita de ajo en polvo.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 1/2 ")
                            }
                            append("cucharaditas de pimienta.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("taza de agua.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("4 ")
                            }
                            append("tomatillos, sin cáscara, enjuagados y cortados en cubitos.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("4 ")
                            }
                            append("tomates ciruela (Roma), cortados en cubitos.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("cebolla blanca pequeña, picada.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("diente de ajo picado.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("2 ")
                            }
                            append("chiles serranos, picados.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1/3 ")
                            }
                            append("taza de cilantro fresco picado.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("cucharada de aceite de oliva.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("cucharadita de orégano mexicano seco.\n\n")

                            append("Jugo de ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("2 ")
                            }
                            append("limones\n")

                            append("Jugo de ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("naranja grande.\n\n")

                            append("Jugo de ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1/2 ")
                            }
                            append("limón.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1/4 ")
                            }
                            append("taza de manteca de cerdo.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1 ")
                            }
                            append("trozo (1 pulgada) de rama de canela.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("2 ")
                            }
                            append("aguacates medianos, sin hueso, pelados y cortados en cubitos.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("24 ")
                            }
                            append("tortillas de maíz.\n\n\n")



// Apartado de Instrucciones
                            withStyle(style = SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                                append("Instrucciones:\n\n")
                            }

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("1.- ")
                            }
                            append("En una olla grande y pesada, agrega la carne de cerdo y sazona con 1 cucharadita de sal, ajo en polvo y 1 cucharadita de pimienta. Agrega 1 taza de agua. Tapar la olla dejándola ligeramente abierta por un lado. Lleva a ebullición a fuego medio-alto; deja que reduzca un poco el fuego y hierve durante unos 20 minutos o hasta que se haya evaporado toda el agua. (No revuelvas la carne mientras se está cociendo al vapor).\n" +
                                    "\n\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("2.- ")
                            }
                            append("En un tazón mediano, agrega los tomatillos, los tomates, la cebolla, el ajo, los chiles serranos, el cilantro, el aceite de oliva, el orégano, el jugo de 1 limón, 1 cucharadita de sal y la 1/2 cucharadita de pimienta restante. Revuelve y sazona con sal al gusto. Cubrelos y reserva.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("3.-  ")
                            }
                            append("En un tazón pequeño, combina el jugo de naranja y el jugo de 1/2 limón. Tan pronto el agua de las carnitas se haya evaporado, agrega la mezcla de jugo. Continúe cocinando hasta que el líquido se haya evaporado.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("4.- ")
                            }
                            append("Retira la tapa y agrega la manteca de cerdo y la rama de canela a la carne de cerdo. Cocina la carne de cerdo hasta que esté bien dorada por todos lados, ve volteandola según sea necesario. Retira la carne de cerdo del aceite con una espumadera y colócala en un plato forrado con toallas de papel. Deja escurrir durante un par de minutos antes de transferirlos a una fuente para servir.\n\n")

                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("5.-  ")
                            }
                            append("En un tazón pequeño, combina los aguacates, el jugo de limón restante y la 1/2 cucharadita de sal restante. Revuelve bien. Sazona al gusto. Calienta las tortillas en un comal y mantenlas calientes en un calentador de tortillas o envueltas en un paño de cocina. Sirve los tacos adornados con salsa fresca y aguacate.\n\n")


                        },
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        },
        bottomBar = {
            BottomNavBarView(navController = navController)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMyRecipeView() {
    MyRecipeView(rememberNavController())
}
