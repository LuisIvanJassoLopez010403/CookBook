package com.example.cookbook.presentation.addrecipe.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.navigation.BottomNavBarView

@Composable
fun AddRecipeView(navController: NavController) {
    Scaffold (
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Text(text = "Add Recipe Screen", fontWeight = FontWeight.Bold, fontSize = 30.sp)
            }
        },
        bottomBar = {
            BottomNavBarView(navController = navController)
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewAddRecipeView() {
    AddRecipeView(rememberNavController())
}