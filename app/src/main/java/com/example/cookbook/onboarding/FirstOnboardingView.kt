package com.example.cookbook.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
fun FirstOnboardingView() {
    Column(
        modifier = Modifier
            .background(Color(0xFFF6F6F6))
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.firstonboardingview),
            contentDescription = "Descripción de la imagen",
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp),
            contentScale = ContentScale.FillBounds
        )

        Spacer(modifier = Modifier.height(95.dp))

        Text(
            text = stringResource(id = R.string.ShareandFindRecipes),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF9800),
            textAlign = TextAlign.Center
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
    }
}