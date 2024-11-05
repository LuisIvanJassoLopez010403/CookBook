package com.example.cookbook.presentation.login.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import com.example.cookbook.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.navigation.Routes
import com.example.cookbook.presentation.login.viewmodels.LoginViewModel
import com.example.cookbook.presentation.login.viewmodels.LoginViewModelFactory
import com.google.android.gms.common.config.GservicesValue.value

@Composable
fun LoginView(navController: NavController) {

    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory())

    val context = LocalContext.current

    loginViewModel.loginResponse.message
    //Variables de TextFields
    var username by remember { mutableStateOf(TextFieldValue(""))}
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }


    if(loginViewModel.state != 0) {
        if (loginViewModel.loginResponse.isSuccess) {
            //Toast.makeText(context,"Login exitoso",Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.HomeView)
            loginViewModel.state = 0
        } else {
            Toast.makeText(context,"Contrasena incorrecta",Toast.LENGTH_SHORT).show()
            loginViewModel.state = 0
        }
    }



    // TextButton para regresar a pantalla de inicio
    Row(
        horizontalArrangement = Arrangement.Start
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Spacer(modifier = Modifier.height(60.dp))

        //Image Logo
        Image(
            painter = painterResource(id = R.drawable.cookbooklogo),
            contentDescription = "Cookbook Logo",
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        if(loginViewModel.isLoading) {
            CircularProgressIndicator(color = Color.Blue)
        }

        // Titulo de Vista
        Text(
            text = stringResource(id = R.string.Login),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // TextField de username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = stringResource(id = R.string.Username)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // TextField de password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(id = R.string.Password)) },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        // TextButton para iniciar proceso de recuperacion de contrasena
        TextButton(onClick = { navController.navigate(Routes.ForgotPasswordView) }) {
            Text(
                text = stringResource(id = R.string.Forgotpasswd),
                fontSize = 18.sp,
                color = Color(0xFFFFA500),
                modifier = Modifier,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Boton de Login
        Button(
            onClick = { loginViewModel.doLogin(username.text, password.text) },
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(25.dp))
                .widthIn(min = 200.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally)
                .height(50.dp)
                .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp))
                .shadow(10.dp, RoundedCornerShape(25.dp)),
            border = BorderStroke(1.dp,Color.White),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
        ) {
            Text(text = stringResource(id = R.string.Login), fontSize = 18.sp, color = Color(0xFFFFA500))
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PreviewLoginView() {
    LoginView(rememberNavController())
}
