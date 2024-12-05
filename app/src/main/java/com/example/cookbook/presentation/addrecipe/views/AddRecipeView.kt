package com.example.cookbook.presentation.addrecipe.views

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.Ingredient
import com.example.cookbook.IngredientDetails
import com.example.cookbook.R
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.addrecipe.viewmodels.AddRecipeViewModel
import com.example.cookbook.presentation.addrecipe.viewmodels.AddRecipeViewModelFactory

@Composable
fun AddRecipeView(navController: NavController) {
    val appContext = LocalContext.current.applicationContext
    val addRecipeViewModel: AddRecipeViewModel = viewModel(factory = AddRecipeViewModelFactory(appContext))
    addRecipeViewModel.recipeResponse.message

    // Variables de TextFields
    var recipeName by remember { mutableStateOf(TextFieldValue("")) }
    var preptime by remember { mutableIntStateOf(0) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var steps by remember { mutableStateOf(TextFieldValue("")) }

    // Variables de Dropdown Menu de Categoria
    var expandedCategories by remember { mutableStateOf(false) }

    // Variables de Dropdown Menu de Ingredientes
    var expandedIngredients by remember { mutableStateOf(false) }
    var selectedIngredients by remember { mutableStateOf<List<Ingredient>>(emptyList()) }

    // Variables de Dropdown Menu de Unidades
    val unitOptions = listOf("kg", "g", "ml", "tbsp", "tsp", "pcs")

    //Variable de Toast
    val context = LocalContext.current

    // Gestionar la navegación basada en el estado
    LaunchedEffect(addRecipeViewModel.state) {
        if (addRecipeViewModel.state != 0) {
            if (addRecipeViewModel.recipeResponse.isSuccess) {
                navController.navigate(Routes.UserView) {
                    popUpTo(Routes.AddRecipeView) { inclusive = true }
                }
            } else {
                Toast.makeText(context, "Error al crear receta", Toast.LENGTH_SHORT).show()
            }
            addRecipeViewModel.resetState()
        }
    }

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

                // Elementos de la vista
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    item {
                        //TextField de recipeName
                        OutlinedTextField(
                            value = recipeName,
                            onValueChange = { recipeName = it },
                            label = { Text(text = "Recipe Name") },
                            shape = RoundedCornerShape(15.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        //Textfield de descripcion
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text(text = "Description") },
                            shape = RoundedCornerShape(15.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Box {
                            OutlinedTextField(
                                value = addRecipeViewModel.categories.find { it.first == addRecipeViewModel.selectedCategoryId }?.second
                                    ?: "",
                                onValueChange = {},
                                label = { Text(text = "Category") },
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expandedCategories = true },
                                readOnly = true,
                                trailingIcon = {
                                    IconButton(onClick = { expandedCategories = !expandedCategories }) {
                                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                                    }
                                }
                            )

                            DropdownMenu(
                                expanded = expandedCategories,
                                onDismissRequest = { expandedCategories = false },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                addRecipeViewModel.categories.forEach { (id, name) ->
                                    DropdownMenuItem(
                                        text = { Text(text = name) },
                                        onClick = {
                                            addRecipeViewModel.selectedCategoryId = id
                                            expandedCategories = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Tiempo de preparacion
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Preparation Time (min)",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(onClick = {
                                if (preptime > 0) preptime -= 5
                            }) {
                                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease Time")
                            }

                            OutlinedTextField(
                                value = preptime.toString(),
                                onValueChange = {},
                                readOnly = true,
                                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier.width(100.dp),
                            )

                            IconButton(onClick = {
                                preptime += 5
                            }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Increase Time")
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Dropdown Menu de ingredientes
                        Box {
                            OutlinedTextField(
                                value = selectedIngredients.joinToString(", ") { it._idIngredient.nameIngredient },
                                onValueChange = {},
                                label = { Text(text = "Ingredients") },
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { expandedIngredients = true },
                                readOnly = true,
                                trailingIcon = {
                                    IconButton(onClick = { expandedIngredients = !expandedIngredients }) {
                                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                                    }
                                }
                            )

                            // Opciones de ingredientes
                            DropdownMenu(
                                expanded = expandedIngredients,
                                onDismissRequest = { expandedIngredients = false },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                addRecipeViewModel.ingredients.forEach { (id, name) ->
                                    DropdownMenuItem(
                                        text = { Text(text = name) },
                                        onClick = {
                                            val existingIngredient = selectedIngredients.find { it._idIngredient._id == id }
                                            val updatedIngredients = if (existingIngredient != null) {
                                                selectedIngredients - existingIngredient
                                            } else {
                                                val newIngredient = Ingredient(
                                                    _idIngredient = IngredientDetails(
                                                        _id = id,
                                                        nameIngredient = name,
                                                        categoy = "",
                                                        __v = 0
                                                    ),
                                                    unit = "",
                                                    amount = 0.0
                                                )
                                                selectedIngredients + newIngredient
                                            }
                                            selectedIngredients = updatedIngredients
                                            addRecipeViewModel.updateSelectedIngredients(updatedIngredients)
                                            expandedIngredients = false
                                        }
                                    )
                                }
                            }
                        }

                        selectedIngredients.forEach { ingredient ->
                            // Variables locales únicas por ingrediente
                            var quantity by remember { mutableStateOf(ingredient.amount.toString()) }
                            var expandedUnitDropdown by remember { mutableStateOf(false) }
                            var selectedUnit by remember { mutableStateOf(ingredient.unit) }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Nombre del ingrediente
                                Text(text = ingredient._idIngredient.nameIngredient, modifier = Modifier.weight(1f))

                                // Campo para ingresar la cantidad
                                OutlinedTextField(
                                    value = quantity,
                                    onValueChange = { newValue ->
                                        quantity = newValue
                                        // Actualizar el valor en el ViewModel
                                        addRecipeViewModel.selectedIngredientDetails = addRecipeViewModel.selectedIngredientDetails.map {
                                            if (it._idIngredient._id == ingredient._idIngredient._id) {
                                                it.copy(amount = newValue.toDoubleOrNull() ?: 0.0)
                                            } else it
                                        }
                                    },
                                    label = { Text("Cantidad") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    shape = RoundedCornerShape(15.dp),
                                    modifier = Modifier.width(100.dp)
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                // Dropdown para seleccionar la unidad
                                Box {
                                    OutlinedTextField(
                                        value = selectedUnit,
                                        onValueChange = {},
                                        readOnly = true,
                                        label = { Text("Unidad") },
                                        trailingIcon = {
                                            IconButton(onClick = { expandedUnitDropdown = !expandedUnitDropdown }) {
                                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                                            }
                                        },
                                        shape = RoundedCornerShape(15.dp),
                                        modifier = Modifier.width(140.dp)
                                    )

                                    DropdownMenu(
                                        expanded = expandedUnitDropdown,
                                        onDismissRequest = { expandedUnitDropdown = false }
                                    ) {
                                        unitOptions.forEach { unitOption ->
                                            DropdownMenuItem(
                                                text = { Text(unitOption) },
                                                onClick = {
                                                    selectedUnit = unitOption
                                                    expandedUnitDropdown = false
                                                    addRecipeViewModel.selectedIngredientDetails = addRecipeViewModel.selectedIngredientDetails.map {
                                                        if (it._idIngredient._id == ingredient._idIngredient._id) {
                                                            it.copy(unit = unitOption)
                                                        } else it
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        //TextField de pasos
                        OutlinedTextField(
                            value = steps,
                            onValueChange = { steps = it },
                            label = { Text(text = "Steps") },
                            shape = RoundedCornerShape(15.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        //Boton de Agregar Receta
                        Button(
                            onClick = {
                                val recipeNameValue = recipeName.text
                                val descriptionValue = description.text
                                val stepsValue = steps.text
                                val categoryId = addRecipeViewModel.selectedCategoryId
                                val ingredientsList = addRecipeViewModel.selectedIngredientDetails
                                val image = "URL de imagen"
                                val video = "URL de video"
                                val grade = 0.0

                                addRecipeViewModel.createRecipe(
                                    nameRecipe = recipeNameValue,
                                    description = descriptionValue,
                                    preptime = preptime,
                                    ingredients = ingredientsList,
                                    steps = stepsValue,
                                    category = categoryId,
                                    image = image,
                                    video = video,
                                    grade = grade
                                )
                            },
                            modifier = Modifier
                                .widthIn(min = 200.dp, max = 300.dp)
                                .align(Alignment.CenterHorizontally)
                                .height(50.dp)
                                .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp))
                                .shadow(10.dp, RoundedCornerShape(25.dp)),
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
                        ) {
                            Text(text = "Add Recipe", fontSize = 18.sp, color = Color(0xFFFFA500))
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                    }
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
fun PreviewAddRecipeView() {
    AddRecipeView(rememberNavController())
}