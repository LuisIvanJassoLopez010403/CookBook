package com.example.cookbook.signup

import androidx.compose.foundation.BorderStroke
import com.example.cookbook.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun SignupScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button
        Text(
            text = "< Back",
            fontSize = 18.sp,
            color = Color(0xFFFFA500),
            modifier = Modifier
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(70.dp))

        //Image Logo
        Image(
            painter = painterResource(id = R.drawable.cookbooklogo3),
            contentDescription = "Cookbook Logo",
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(40.dp))

// Title Text
        Text(
            text = "Sign up",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )


        Spacer(modifier = Modifier.height(16.dp))


// Email Input
        var email by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email:") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))


// Username Input
        var username by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username:") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))


// Password Input
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password:") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))


// Username Input
        var confirm by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text(text = "Confirm Password:") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(32.dp))


        // Sign up Button
        Button(
            onClick = {  },
            modifier = Modifier
                .shadow(10.dp)
                .widthIn(min = 200.dp, max = 300.dp)
                .align(Alignment.CenterHorizontally)
                .height(50.dp),
            border = BorderStroke(1.dp,Color.White),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
        ) {
            Text(text = "Sign Up", fontSize = 18.sp, color = Color(0xFFFFA500))
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewSignupScreen() {
    SignupScreen()
}
