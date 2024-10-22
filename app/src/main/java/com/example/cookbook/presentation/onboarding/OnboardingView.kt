package com.example.cookbook.presentation.onboarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook.R
import kotlinx.coroutines.launch
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cookbook.navigation.Routes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingView(navController: NavController,onboardingViewModel: OnboardingViewModel = viewModel()) {
    val pager = 3
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    val isOnboardingCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState()

    LaunchedEffect(isOnboardingCompleted) {
        if (isOnboardingCompleted) {
            navController.navigate(Routes.TitleView)
        }
    }

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
        repeat(pager) { page ->
            // Animar el tamaño de cada indicador
            val size by animateDpAsState(
                targetValue = if (pagerState.currentPage == page) 22.dp else 12.dp
            )

            Box(
                modifier = Modifier
                    .size(size, 12.dp)
                    .background(
                        if (pagerState.currentPage == page) Color(0xFFFFA500) else Color.Gray,
                        RoundedCornerShape(6.dp)
                    )
                    .shadow(10.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
    }

    if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(bottom = 35.dp)
                .padding(start = 25.dp, end = 25.dp)
        ) {
            if (pagerState.currentPage == 0){
                TextButton(
                    {
                        navController.navigate(Routes.TitleView)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.Skip),
                        color = Color.Gray,
                        fontSize = 23.sp,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                ) {
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
                .padding(bottom = 35.dp)
                .padding(start = 25.dp, end = 25.dp)
        ){
            Button(
                onClick = {
                    navController.navigate(Routes.TitleView)
                    onboardingViewModel.completeOnboarding()},
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