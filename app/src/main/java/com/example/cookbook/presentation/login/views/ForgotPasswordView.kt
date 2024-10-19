package com.example.cookbook.presentation.login.views

import androidx.compose.foundation.BorderStroke
import com.example.cookbook.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.navigation.Routes

@Composable
fun ForgotPasswordView(navController: NavController) {
    // Variables de TextFields
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var username by remember { mutableStateOf(TextFieldValue("")) }

    // TextButton para regresar a pantalla de Login
    Row(
        horizontalArrangement = Arrangement.Start
    ) {
        TextButton(onClick = { navController.navigate(Routes.LoginView) }) {
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

        // Titulo de Vista
        Text(
            text = stringResource(id = R.string.Forgotpasswd),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // TextField de email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(id = R.string.Email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // TextField de username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = stringResource(id = R.string.Username)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Boton de envio de codigo de verificacion
        Button(
            onClick = { navController.navigate(Routes.VerificationCodeView) },
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
            Text(text = stringResource(id = R.string.Sendverificationcode), fontSize = 18.sp, color = Color(0xFFFFA500))
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun PreviewForgotPasswordView() {
    ForgotPasswordView(rememberNavController())
}