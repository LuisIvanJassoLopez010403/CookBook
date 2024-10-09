package com.example.cookbook.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.R
import kotlinx.coroutines.launch
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.cookbook.navigation.Routes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingView(navController: NavController) {

    val offset = Offset(x = 2f, y = 2f)

    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState
    ) { page ->
        when (page) {
            0 -> FirstOnboardingView()
            1 -> SecondOnboardingView()
            2 -> ThirdOnboardingView()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            if(pagerState.currentPage == 0) {
                Modifier
                    .size(22.dp, 12.dp)
                    .background(Color(0xFFFFA500), RoundedCornerShape(6.dp))
                    .shadow(10.dp)
            } else {
                Modifier
                    .size(12.dp, 12.dp)
                    .background(Color.Gray, RoundedCornerShape(6.dp))
                    .shadow(10.dp)
            }
        )
        Spacer(modifier = Modifier.width(6.dp))
        Box(
            if(pagerState.currentPage == 1) {
                Modifier
                    .size(22.dp, 12.dp)
                    .background(Color(0xFFFFA500), RoundedCornerShape(6.dp))
                    .shadow(10.dp)
            } else {
                Modifier
                    .size(12.dp, 12.dp)
                    .background(Color.Gray, RoundedCornerShape(6.dp))
                    .shadow(10.dp)
            }
        )
        Spacer(modifier = Modifier.width(6.dp))
        Box(
            if(pagerState.currentPage == 2) {
                Modifier
                    .size(22.dp, 12.dp)
                    .background(Color(0xFFFFA500), RoundedCornerShape(6.dp))
                    .shadow(10.dp)
            } else {
                Modifier
                    .size(12.dp, 12.dp)
                    .background(Color.Gray, RoundedCornerShape(6.dp))
                    .shadow(10.dp)
            }
        )
    }

    if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(bottom = 70.dp)
                .padding(start = 25.dp, end = 25.dp)
        ) {
            TextButton(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            ) {
                if (pagerState.currentPage == 0) {
                    Text(
                        text = stringResource(id = R.string.Skip),
                        color = Color.Gray,
                        fontSize = 23.sp,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.Back),
                        color = Color.Gray,
                        fontSize = 23.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.padding(50.dp))

            TextButton(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            ) {
                Text(
                    text = stringResource(id = R.string.Next),
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFA500),
                    textAlign = TextAlign.Center
                )
            }
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(bottom = 70.dp)
                .padding(start = 25.dp, end = 25.dp)
        ){
            Button(
                onClick = { navController.navigate(Routes.TitleView) },
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(1.5.dp, Color(0xFFFFA500), RoundedCornerShape(25.dp))
                    .shadow(10.dp, RoundedCornerShape(25.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.gstarted),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFA500),
                    fontSize = 23.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}