package com.example.cookbook.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.onboarding.Onboarding1Screen
import com.example.cookbook.onboarding.SecondOnboardingScreen
import com.example.cookbook.onboarding.ThirdOnboardingView


@Composable
fun MyAppNavigationView() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.Onboarding1Screen, builder = {
            composable(Routes.Onboarding1Screen) {
                Onboarding1Screen(navController)
            }
            composable(Routes.SecondOnboardingScreen) {
                SecondOnboardingScreen(navController)
            }
            composable(Routes.ThirdOnboardingView) {
                ThirdOnboardingView(navController)
            }
        })
}