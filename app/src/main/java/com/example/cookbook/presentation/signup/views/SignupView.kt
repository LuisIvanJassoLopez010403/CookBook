package com.example.cookbook.presentation.signup.views

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.signup.viewmodels.SignupViewModel
import com.example.cookbook.presentation.signup.viewmodels.SignupViewModelFactory
import java.util.*

@Composable
fun SignupView(navController: NavController) {
    //Variables de ViewModel
    val signupViewModel: SignupViewModel = viewModel(factory = SignupViewModelFactory())
    signupViewModel.signupResponse.message

    //Variables de TextFields
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var birthDate by remember { mutableStateOf("") }

    //Variable de Toast
    val context = LocalContext.current

    //Calendario para fecha de nacimiento
    val calendar = Calendar.getInstance() //CAlendar para obtener la fecha actual del sistema, inicializa el DatePickerDialog
    val datePickerDialog = DatePickerDialog(      //Se utiliza DatePickerDialog, interfaz que permite a los usuarios seleccionar dia, mes y año
        navController.context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            birthDate = "$dayOfMonth/${month + 1}/$year"               //Agrupa los valores seleccionados, el mes se maneja de 0 a 11 por eso el +1
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Variables de Dropdown Menu
    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("") }
    val genderOption1 = stringResource(id = R.string.GenderOp1)
    val genderOption2 = stringResource(id = R.string.GenderOp2)
    val genderOption3 = stringResource(id = R.string.GenderOp3)
    val genderOptions = listOf(genderOption1,genderOption2,genderOption3)

    //Variable de keyboard
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // ViewModel Logica
    if(signupViewModel.state != 0) {
        if (signupViewModel.signupResponse.isSuccess) {
            navController.navigate(Routes.LoginView)
            signupViewModel.state = 0
        } else {
            Toast.makeText(context,"Este usuario ya existe", Toast.LENGTH_SHORT).show()
            signupViewModel.state = 0
        }
    }

    // TextButton para regresar a pantalla de inicio
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = { focusManager.clearFocus() })
        }
    ) {
        TextButton(onClick = { navController.navigate(Routes.TitleView) }) {
            Text(
                text = "< " + stringResource(id = R.string.Back),
                fontSize = 18.sp,
                color = Color(0xFFFFA500),
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }
    }
            //Diseño de la pantalla

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        // Image Logo
        Image(
            painter = painterResource(id = R.drawable.cookbooklogo),
            contentDescription = "Cookbook Logo",
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Titulo de vista
        Text(
            text = stringResource(id = R.string.Signup),
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

                //TextField de Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = stringResource(id = R.string.Email)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                //TextField de username
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(text = stringResource(id = R.string.Username)) },
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

                //TextField de Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it }, //Por cada caracter ingresado el lambda se ejecuta
                    label = { Text(text = stringResource(id = R.string.Password)) },
                    singleLine = true,          //Para que tod0 se ejecute en una misma linea, mostrarselo a Jimenez para el apartado de ingredientes
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), //Para que se vea o no la contraseña con puntitos, si es true utiliza el "VisualTransformation.None" que muestra la contraseña, si es false la oculta con "PasswordVisualTransformation"
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {                            //Icono de la derecha para mostrar la contraseña
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                            Icon(imageVector  = image, description)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // TextField de Confirm Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text(text = stringResource(id = R.string.ConfirmPassword)) },
                    singleLine = true,
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        val image = if (confirmPasswordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (confirmPasswordVisible) "Hide password" else "Show password"

                        IconButton(onClick = {confirmPasswordVisible = !confirmPasswordVisible}){
                            Icon(imageVector  = image, description)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Spacer(modifier = Modifier.height(10.dp))

                // TextField de BirthDate
                OutlinedTextField(
                    value = birthDate,
                    onValueChange = {},
                    label = { Text(text = stringResource(id = R.string.Birthdate)) },
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
                        label = { Text(text = stringResource(id = R.string.Gender)) },
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
                    onClick = {
                        if (password.text == confirmPassword.text) {
                            signupViewModel.doSignup(email.text, username.text, password.text, birthDate, selectedGender)
                        } else {
                            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .widthIn(min = 200.dp, max = 300.dp)
                        .align(Alignment.CenterHorizontally)
                        .height(50.dp)
                        .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp))
                        .shadow(10.dp, RoundedCornerShape(25.dp)),
                    border = BorderStroke(1.dp,Color.White),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
                ) {
                    Text(text = stringResource(id = R.string.Signup), fontSize = 18.sp, color = Color(0xFFFFA500))
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
