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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.R

class Onboarding1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Onboarding1Screen()
        }
    }
}

@Composable
fun Onboarding1Screen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Image at the top
        Image(
            painter = painterResource(id = R.drawable.aonboarding),
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Indicator
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .shadow(10.dp)
        ) {
            Box(modifier = Modifier.size(18.dp, 12.dp).background(Color(0xFFFF9800), RoundedCornerShape(6.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier.size(12.dp, 12.dp).background(Color.Gray, RoundedCornerShape(6.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier.size(12.dp, 12.dp).background(Color.Gray, RoundedCornerShape(6.dp)))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle
        Text(
            text = "Share & Find Recipes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF9800) // Orange color
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description Text
        Text(
            text = "Share your recipes with the world or find your next favorite meal by exploring other users’ profiles.",
            fontSize = 14.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.weight(1f)) // Spacer to push buttons to the bottom

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* Handle Skip logic */ },
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = "Skip", color = Color.White)
            }

            Button(
                onClick = { /* Handle Next logic */ },
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA500)) // Orange color
            ) {
                Text(text = "Next", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding1Screen() {
    Onboarding1Screen()
}