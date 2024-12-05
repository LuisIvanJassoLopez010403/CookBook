package com.example.cookbook.presentation.title

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.R
import com.example.cookbook.navigation.Routes

@Composable
fun TitleView(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image Logo
            Image(
                painter = painterResource(id = R.drawable.cookbooklogo),
                contentDescription = "Cookbook Logo",
                modifier = Modifier
                    .size(350.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Signup Button
            Button(
                onClick = { navController.navigate(Routes.SignupView) },
                modifier = Modifier
                    .shadow(10.dp, RoundedCornerShape(25.dp))
                    .width(350.dp)
                    .height(50.dp),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
            ) {
                Text(text = stringResource(id = R.string.Signup), fontSize = 18.sp, color = Color(0xFFFFA500))
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Login Button
            Button(
                onClick = { navController.navigate(Routes.LoginView) },
                modifier = Modifier
                    .shadow(10.dp, RoundedCornerShape(25.dp))
                    .width(350.dp)
                    .height(50.dp),
                border = BorderStroke(1.dp, color = Color(0xFFFFA500)),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA500))
            ) {
                Text(text = stringResource(id = R.string.Login), fontSize = 18.sp, color = Color(0xFFFFFFFF))
            }
        }

        // "Acerca De" Textbutton
        TextButton(
            onClick = { navController.navigate(Routes.AboutView) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            Text(text = "More About Us", fontSize = 16.sp, color = Color.Blue)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTitleView() {
    TitleView(rememberNavController())
}
