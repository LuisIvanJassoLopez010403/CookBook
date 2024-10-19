package com.example.cookbook.presentation.signup

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
    //Variables de TextFields
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var birthDate by remember { mutableStateOf("") }

    //Calendario para fecha de nacimiento
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        navController.context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            birthDate = "$dayOfMonth/${month + 1}/$year"
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Variables de Dropdown Menu
    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("") }
    val genderOptions = listOf("Male", "Female", "Other")

    // TextButton para regresar a pantalla de inicio
    Row(
        horizontalArrangement = Arrangement.Start
    ) {
        TextButton(onClick = { navController.navigate(Routes.TitleView) }) {
            Text(
                text = "< Back",
                fontSize = 18.sp,
                color = Color(0xFFFFA500),
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // Logo
        Image(
            painter = painterResource(id = R.drawable.cookbooklogo3),
            contentDescription = "Cookbook Logo",
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Titulo de vista
        Text(
            text = "Sign up",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // TextFields y SignUp Button con scroll
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                //TextField de username
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(text = "Username") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                //TextField de Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                //TextField de Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                //TextField de Confirm Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text(text = "Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // TextField de Address
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text(text = "Address") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                // TextField de BirthDate
                OutlinedTextField(
                    value = birthDate,
                    onValueChange = {},
                    label = { Text(text = "Birthdate") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                //Dropdown Menu
                Box {
                    //TextField de Dropdown Menu
                    OutlinedTextField(
                        value = selectedGender,
                        onValueChange = {},
                        label = { Text(text = "Gender") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                            }
                        }
                    )

                    // Opciones de Dropdown Menu
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        genderOptions.forEach { gender ->
                            DropdownMenuItem(
                                text = { Text(text = gender) },
                                onClick = {
                                    selectedGender = gender
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Boton de SignUp
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

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

//Preview
@Preview(showBackground = true)
@Composable
fun PreviewSignupView() {
    SignupView(rememberNavController())
}
