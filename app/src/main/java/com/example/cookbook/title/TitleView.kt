package com.example.cookbook.title

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookbook.R
import com.example.cookbook.navigation.Routes

@Composable
fun TitleView(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cookbooklogo3),
            contentDescription = "Cookbook Logo",
            modifier = Modifier
                .size(350.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {  navController.navigate(Routes.UserView) },
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(25.dp))
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(1.dp, Color.White),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
        ) {
            Text(text = "Sign Up", fontSize = 18.sp, color = Color(0xFFFFA500))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { navController.navigate(Routes.BuscadorinicialView) },
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(25.dp))
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(1.dp, color = Color(0xFFFFA500)),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFA500))
        ) {
            Text(text = "Login", fontSize = 18.sp, color = Color(0xFFFFFFFF))
        }
    }
}