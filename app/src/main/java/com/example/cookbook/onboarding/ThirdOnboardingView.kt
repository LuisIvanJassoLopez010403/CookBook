package com.example.cookbook.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cookbook.R

@Composable
fun ThirdOnboardingView(navController: NavController){
    val offset = Offset(5.0f, 10.0f)

    Column(
        Modifier.background(Color(0xFFF6F6F6)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.thirdonboardingimage),
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
            Box(modifier = Modifier.size(12.dp, 12.dp).background(Color.Gray, RoundedCornerShape(6.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier.size(12.dp, 12.dp).background(Color.Gray, RoundedCornerShape(6.dp)))
            Spacer(modifier = Modifier.width(6.dp))
            Box(modifier = Modifier.size(22.dp, 12.dp).background(Color(0xFFFF9800), RoundedCornerShape(6.dp)))
        }

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "Create Lists",
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
            text = "Save your favorite recipes\n" +
                    "and create your own lists to\n" +
                    "accommodate each recipe for\n" +
                    "the right occasion.",
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {  },
            modifier = Modifier
                .shadow(10.dp)
                .height(50.dp)
                .width(270.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(Color.White) // Orange color
        ) {
            Text(text = "Get Started", fontSize = 22.sp, color = Color(0xFFFFA500))
        }
    }
}
