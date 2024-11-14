package com.example.cookbook.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.R

@Composable
fun ThirdOnboardingView() {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ){
        Column(
            modifier = Modifier
                .background(Color(0xFFF6F6F6))
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.thirdonboardingview),
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp),
                contentScale = ContentScale.FillBounds
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 100.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFF6F6F6)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(id = R.string.CreLists),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF9800),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = stringResource(id = R.string.secdescription),
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
