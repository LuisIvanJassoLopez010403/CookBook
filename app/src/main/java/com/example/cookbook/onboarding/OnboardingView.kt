package com.example.cookbook.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookbook.R

@Composable
fun FirstOnboardingView(navController: NavController) {
    val offset = Offset(5.0f, 10.0f)

    Column(
        Modifier.background(Color(0xFFF6F6F6)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.aaonboarding),
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .shadow(10.dp)
        ) {
            Box(modifier = Modifier.size(22.dp, 12.dp).background(Color(0xFFFF9800), RoundedCornerShape(6.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier.size(12.dp, 12.dp).background(Color.Gray, RoundedCornerShape(6.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier.size(12.dp, 12.dp).background(Color.Gray, RoundedCornerShape(6.dp)))
        }

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = stringResource(id = R.string.ShareandFindRecipes),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF9800),
            //style = TextStyle(
            //    fontSize = 24.sp,
            //    shadow = Shadow(
            //        color = Color.Black.copy(alpha = 0.25f), offset = offset, blurRadius = 3f
            //    )
            //)
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Share your recipes with the\n" +
                    "world or find your next\n" +
                    "favorite meal by exploring\n" +
                    "other users’ profiles.",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(bottom = 70.dp)
        ) {
            TextButton(
                onClick = { /* Handle Skip logic */ }
            ) {
                Text(
                    text = stringResource(id = R.string.Skip),
                    color = Color.Gray,
                    fontSize = 23.sp
                )
            }

            Spacer(modifier = Modifier.padding(100.dp))

            TextButton(
                onClick = { navController.navigate("SecondOnboardingScreen") }
            ) {
                Text(
                    text = stringResource(id = R.string.Skip),
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    color = Color(0xFFFFA500)
                )
            }
        }
    }
}
