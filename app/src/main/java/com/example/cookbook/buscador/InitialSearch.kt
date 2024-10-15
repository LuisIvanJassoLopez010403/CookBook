package com.example.cookbook.buscador

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.myRecipe.MyRecipeView

@Composable
fun BuscadorinicialView(navController: NavController) {
    var text by remember { mutableStateOf("") }

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            //.background(Color(0xFFF6F6F6))
            .fillMaxHeight()
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .padding(10.dp)
        ){
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.settingicon),
                    contentDescription = "Settings",
                    tint = Color(0xFFFFA500),
                    modifier = Modifier.size(50.dp)
                )
            }
        }

        Box (
            modifier = Modifier
                .padding(0.dp) // Asegura un buen espacio alrededor
                .shadow(8.dp, RoundedCornerShape(50.dp), clip = false) // Sombra más suave
                .background(Color.White, RoundedCornerShape(50.dp))
        ){
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .width(365.dp),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.Search),
                        color = Color.Gray
                    )
                },
                shape = RoundedCornerShape(25.dp),
                label = {
                    Text(text = stringResource(id = R.string.Search))
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BuscadorinicialView() {
    BuscadorinicialView(rememberNavController())
}