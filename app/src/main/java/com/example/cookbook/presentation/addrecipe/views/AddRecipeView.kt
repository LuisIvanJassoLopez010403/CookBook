package com.example.cookbook.presentation.addrecipe.views

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.cookbook.Ingredient
import com.example.cookbook.IngredientDetails
import com.example.cookbook.navigation.BottomNavBarView
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.addrecipe.viewmodels.AddRecipeViewModel
import com.example.cookbook.presentation.addrecipe.viewmodels.AddRecipeViewModelFactory
import com.example.cookbook.utils.uriToBase64

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

    // Variables de Dropdown Menu de Unidades
    val unitOptions = listOf("kilograms", "grams", "ounces", "milliliter", "liter", "cup", "table spoon", "pieces", "slices")

    //Variable de Toast
    val context = LocalContext.current

    // Variables de keyboard
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Variable de imagen
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            addRecipeViewModel.selectedImage = uri.toString()

            val base64Image = uriToBase64(appContext, uri)
            if (base64Image != null) {
                addRecipeViewModel.base64Image = base64Image
            }
        }
    }


    // Gestionar la navegación basada en el estado
    LaunchedEffect(addRecipeViewModel.state) {
        if (addRecipeViewModel.state != 0) {
            if (addRecipeViewModel.recipeResponse.isSuccess) {
                navController.navigate(Routes.UserView) {
                    popUpTo(Routes.AddRecipeView) { inclusive = true }
                }
            } else {
                Toast.makeText(context, "Ha ocurrido un error al crear la receta o un campo esta vacío", Toast.LENGTH_SHORT).show()
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
                    .padding(innerPadding)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = { focusManager.clearFocus() })
                    }
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
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        //Textfield de descripcion
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text(text = "Description") },
                            shape = RoundedCornerShape(15.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Dropdown de categorias
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

                        // Dropdown de ingredientes
                        Box {
                            OutlinedTextField(
                                value = addRecipeViewModel.selectedIngredientDetails.joinToString(", ") { it._idIngredient.nameIngredient },
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
                                    val alreadySelected = addRecipeViewModel.selectedIngredientDetails.any { it._idIngredient._id == id }

                                    DropdownMenuItem(
                                        text = { Text(name) },
                                        onClick = {
                                            if (!alreadySelected) {
                                                val newIngredient = Ingredient(
                                                    _idIngredient = IngredientDetails(
                                                        _id = id,
                                                        nameIngredient = name,
                                                        categoy = "",
                                                        __v = 0
                                                    ),
                                                    unit = "",   // Sin valor inicial
                                                    amount = 0.0 // Sin valor inicial
                                                )
                                                addRecipeViewModel.addIngredient(newIngredient)
                                            }
                                            expandedIngredients = false
                                        }
                                    )
                                }
                            }
                        }

                        // Campos de Ingredientes
                        addRecipeViewModel.selectedIngredientDetails.forEach { ingredient ->
                            val amount = addRecipeViewModel.ingredientDetails[ingredient._idIngredient._id]?.amount?.toString() ?: ""
                            val unit = addRecipeViewModel.ingredientDetails[ingredient._idIngredient._id]?.unit ?: ""
                            var expandedUnitDropdown by remember { mutableStateOf(false) }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = ingredient._idIngredient.nameIngredient,
                                    modifier = Modifier.weight(1f)
                                )

                                // Textfield de cantidad
                                OutlinedTextField(
                                    value = if (amount == "0.0") "" else amount,
                                    onValueChange = { newValue ->
                                        addRecipeViewModel.updateIngredientDetails(
                                            ingredient._idIngredient._id,
                                            newValue.toDoubleOrNull() ?: 0.0,
                                            unit
                                        )
                                    },
                                    label = { Text("Cantidad") },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            keyboardController?.hide()
                                        }
                                    ),
                                    shape = RoundedCornerShape(15.dp),
                                    modifier = Modifier.width(100.dp)
                                )


                                Spacer(modifier = Modifier.width(10.dp))

                                // Dropdown de unidad
                                Box {
                                    OutlinedTextField(
                                        value = unit,
                                        onValueChange = {},
                                        readOnly = true,
                                        label = { Text("Unidad") },
                                        trailingIcon = {
                                            IconButton(onClick = { expandedUnitDropdown = !expandedUnitDropdown }) {
                                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                                            }
                                        },
                                        shape = RoundedCornerShape(15.dp),
                                        modifier = Modifier.width(150.dp)
                                    )

                                    DropdownMenu(
                                        expanded = expandedUnitDropdown,
                                        onDismissRequest = { expandedUnitDropdown = false }
                                    ) {
                                        unitOptions.forEach { unitOption ->
                                            DropdownMenuItem(
                                                text = { Text(unitOption) },
                                                onClick = {
                                                    addRecipeViewModel.updateIngredientDetails(
                                                        ingredient._idIngredient._id,
                                                        amount.toDoubleOrNull() ?: 0.0,
                                                        unitOption
                                                    )
                                                    expandedUnitDropdown = false
                                                }
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                // Botón eliminar ingrediente
                                IconButton(onClick = {
                                    addRecipeViewModel.removeIngredient(ingredient._idIngredient._id)
                                }) {
                                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Eliminar Ingrediente")
                                }
                            }
                        }

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

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .border(2.dp, Color.Gray, RoundedCornerShape(10.dp))
                                .padding(5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            val imagePainter = rememberAsyncImagePainter(
                                model = if (addRecipeViewModel.selectedImage.isNotEmpty()) {
                                    addRecipeViewModel.selectedImage
                                } else {
                                    null
                                }
                            )

                            Image(
                                painter = imagePainter,
                                contentDescription = "Imagen Seleccionada",
                                modifier = Modifier.size(140.dp)
                            )
                        }



                        Spacer(modifier = Modifier.height(10.dp))

                        TextButton(
                            onClick = { launcher.launch("image/*") }
                        ) {
                            Text(
                                text = "Select Image",
                                fontSize = 18.sp,
                                color = Color(0xFFFFA500)
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        //Boton de Agregar Receta
                        Button(
                            onClick = {
                                val recipeNameValue = recipeName.text
                                val descriptionValue = description.text
                                val stepsValue = steps.text
                                val categoryId = addRecipeViewModel.selectedCategoryId
                                val ingredientsList = addRecipeViewModel.selectedIngredientDetails
                                val video = "URL de video"
                                val grade = 0.0

                                addRecipeViewModel.createRecipe(
                                    nameRecipe = recipeNameValue,
                                    description = descriptionValue,
                                    preptime = preptime,
                                    ingredients = ingredientsList,
                                    steps = stepsValue,
                                    category = categoryId,
                                    image = addRecipeViewModel.base64Image,
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