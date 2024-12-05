package com.example.cookbook.presentation.lists.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cookbook.presentation.lists.viewmodels.CreateListViewModel
import com.example.cookbook.presentation.lists.viewmodels.CreateListViewModelFactory

@Composable
fun CreateListView(navController: NavController, recipeId: String? = null) {
    val appContext = LocalContext.current.applicationContext
    val createListViewModel: CreateListViewModel = viewModel(factory = CreateListViewModelFactory(appContext))

    var listName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Crear Nueva Lista",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = listName,
            onValueChange = { listName = it },
            label = { Text(text = "Nombre de la Lista") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Descripción (Opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                createListViewModel.createList(
                    nameList = listName,
                    recipes = recipeId?.let { listOf(it) } ?: emptyList(),
                    description = description
                )
                navController.navigateUp() // Navegar de regreso
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Crear Lista")
        }
    }
}
