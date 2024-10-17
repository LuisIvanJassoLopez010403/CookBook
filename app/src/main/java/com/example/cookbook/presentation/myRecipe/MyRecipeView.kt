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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R

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
                // "Back" Text
                Text(
                    text = "Back",
                    color = Color(0xFFFF9800),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                // "Edit" Button
                Button(
                    onClick = { /* Acción de edición */ }, // Navega hasta la pantalla de edición del usuario.
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF9800)),
                    modifier = Modifier
                        .width(80.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(50)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Edit",
                        color = Color.White
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

                // Receta
                item {
                    Text(
                        text = """
                            1. Calienta las tortillas de maíz.
                            2. Cocina la carne de cerdo (carnitas) en un sartén.
                            3. Añade la carne sobre las tortillas.
                            4. Agrega cebolla, cilantro y salsa al gusto.
                            5. Sirve caliente.
                        """.trimIndent(),
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMyRecipeView() {
    MyRecipeView(rememberNavController())
}
