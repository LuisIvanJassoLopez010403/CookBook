package com.example.cookbook.presentation.lists.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.presentation.lists.viewmodels.CreateListViewModel
import com.example.cookbook.presentation.lists.viewmodels.CreateListViewModelFactory

@Composable
fun CreateListView(navController: NavController, recipeId: String? = null) {
    val appContext = LocalContext.current.applicationContext
    val createListViewModel: CreateListViewModel = viewModel(factory = CreateListViewModelFactory(appContext))

    var listName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        content = { innerPadding ->


            // Logo de app
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(innerPadding),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text(
                            text = "< " + stringResource(id = R.string.Back),
                            fontSize = 18.sp,
                            color = Color(0xFFFFA500),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                    Text(
                        text = stringResource(id = R.string.createlist),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = listName,
                        onValueChange = { listName = it },
                        label = { Text(text = stringResource(id = R.string.namelist)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text(text = stringResource(id = R.string.opdesc)) },
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
                            navController.navigateUp()
                        },
                        modifier = Modifier
                            .shadow(10.dp, RoundedCornerShape(25.dp))
                            .align(Alignment.CenterHorizontally)
                            .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp)),
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
                    ) {
                        Text(
                            text = stringResource(id = R.string.crelist),
                            fontSize = 18.sp,
                            color = Color(0xFFFFA500)
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomNavBarView(navController = navController)
        }
    )
}
