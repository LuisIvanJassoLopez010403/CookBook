package com.example.cookbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cookbook.buscador.BuscadorinicialView
import com.example.cookbook.onboarding.FirstOnboardingView
import com.example.cookbook.onboarding.OnboardingView
import com.example.cookbook.onboarding.SecondOnboardingView
import com.example.cookbook.onboarding.ThirdOnboardingView
import com.example.cookbook.title.TitleView
import com.example.cookbook.user.UserView

// Prueba
@Composable
fun MyAppNavigationView() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.OnboardingView, builder = {
            composable(Routes.FirstOnboardingView) {
                FirstOnboardingView()
            }
            composable(Routes.SecondOnboardingView) {
                SecondOnboardingView()
            }
            composable(Routes.ThirdOnboardingView) {
                ThirdOnboardingView()
            }
            composable(Routes.TitleView) {
                TitleView(navController)
            }
            composable(Routes.UserView) {
                UserView(navController)
            }
            composable(Routes.OnboardingView) {
                OnboardingView(navController)
            }
            composable(Routes.OnboardingView) {
                OnboardingView(navController)
            }
            composable(Routes.BuscadorinicialView) {
                BuscadorinicialView(navController)
            }
        })
}