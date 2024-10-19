package com.example.cookbook.presentation.signup

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.navigation.Routes
import java.util.*

@Composable
fun SignupView(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var adress by remember { mutableStateOf(TextFieldValue("")) }
    var birthDate by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        navController.context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            birthDate = "$dayOfMonth/${month + 1}/$year"
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("") }
    val genderOptions = listOf("Male", "Female", "Other")

    Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        // Back Button
        TextButton(
            onClick = { navController.navigate(Routes.TitleView) },
            modifier = Modifier.align(Alignment.TopStart) // Alinea el botón a la izquierda
        ) {
            Text(
                text = "< Back",
                fontSize = 18.sp,
                color = Color(0xFFFFA500),
                textAlign = TextAlign.Left
            )
        }

        // Column for the rest of the content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp), // Espacio para que el botón no se superponga con el contenido
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image Logo
            Image(
                painter = painterResource(id = R.drawable.cookbooklogo3),
                contentDescription = "Cookbook Logo",
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Title Text
            Text(
                text = "Sign up",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email:") },
                modifier = Modifier.fillMaxWidth()
            )

            // Password Input
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password:") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            // Confirm Password Input
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password:") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            // Domicilio Input
            OutlinedTextField(
                value = adress,
                onValueChange = { adress = it },
                label = { Text(text = "Address:") },
                modifier = Modifier.fillMaxWidth()
            )

            // Date Picker for Birthdate
            OutlinedTextField(
                value = birthDate,
                onValueChange = {},
                label = { Text(text = "Select Birthdate") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,  // Para que el campo sea de solo lectura
                trailingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null)
                    }
                }
            )

            // Gender Dropdown Menu
            OutlinedTextField(
                value = selectedGender,
                onValueChange = {},
                label = { Text(text = "Select Gender") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
            )

            // Gender Dropdown Menu items
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                genderOptions.forEach { gender ->
                    DropdownMenuItem(onClick = {
                        selectedGender = gender
                        expanded = false
                    }) {
                        Text(text = gender)
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            //Boton de Sign Up
            Button(
                onClick = { navController.navigate(Routes.TitleView) },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp))
                    .shadow(10.dp, RoundedCornerShape(25.dp))
            ) {
                Text(text = "Sign Up", fontSize = 18.sp, color = Color(0xFFFFA500))
            }
        }
    }
}
//Falta implementar datos en el Drop menu
fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun PreviewSignupView() {
    SignupView(rememberNavController())
}
