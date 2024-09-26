package com.example.cookbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.onboarding.FirstOnboardingView
import com.example.cookbook.onboarding.SecondOnboardingScreen
import com.example.cookbook.onboarding.ThirdOnboardingView


@Composable
fun MyAppNavigationView() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.FirstOnboardingView, builder = {
            composable(Routes.FirstOnboardingView) {
                FirstOnboardingView(navController)
            }
            composable(Routes.SecondOnboardingView) {
                SecondOnboardingScreen(navController)
            }
            composable(Routes.ThirdOnboardingView) {
                ThirdOnboardingView(navController)
            }
        })
}